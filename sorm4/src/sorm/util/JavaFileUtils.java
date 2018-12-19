package sorm.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import sorm.bean.ColumnInfo;
import sorm.bean.JavaFieldGetSet;
import sorm.bean.TableInfo;
import sorm.core.DBManager;
import sorm.core.MySqlTypeConvertor;
import sorm.core.TypeConvertor;

/**
 * 封装了Java文件（源代码）常用的操作
 * @author sxj
 *
 */
public class JavaFileUtils {
	private final static String BLANK = " ";
	
	/**
	 * 根据字段信息生成java属性信息。如varchar username--> private String username; 以及相应的set和get方法源码
	 * @param column 字段信息
	 * @param convertor 类型转化器
	 * @return java属性和set/get方法源码
	 */
	public static JavaFieldGetSet createFieldGetSetSRC(ColumnInfo column, 
			TypeConvertor convertor){
		
		JavaFieldGetSet jfgs = new JavaFieldGetSet();
		
		String javaFieldType = convertor.databaseType2JavaType(column.getDataType());
		
		//构建field属性代码
		jfgs.setFieldInfo("\tprivate" + BLANK + javaFieldType + BLANK + column.getName() + ";\n");
		
		//构建getter方法代码
		//public String getUsername(){return username;}
		StringBuilder getSrc = new StringBuilder();
		getSrc.append("\tpublic" + BLANK + javaFieldType + BLANK + 
					"get" + StringUtils.firstChar2UpperCase(column.getName()) +"()"
				+"{\n") ;
		getSrc.append("\t\treturn" + BLANK + column.getName() + ";" + "\n\t}");
		
		jfgs.setGetInfo(getSrc.toString());
		
		//构建setter方法代码
		//public void setName(String name){this.name =  name ;}
		StringBuilder setSrc = new StringBuilder();
		setSrc.append("\tpublic" + BLANK + "void" + BLANK 
				+ "set" + StringUtils.firstChar2UpperCase(column.getName())
				+ "(" + javaFieldType + BLANK + column.getName() + "){\n") ;
		setSrc.append("\t\tthis." + column.getName() + " = " + column.getName() + ";" + "\n\t}");
		jfgs.setSetInfo(setSrc.toString());
		return jfgs;
		
		}

	/**
	 * 根据表信息生成java类的源代码
	 * @param tableInfo 表信息
	 * @param convertor 数据类型转化器
	 * @return java类的源码
	 */
	public static String createJavaSrc(TableInfo tableInfo, TypeConvertor convertor){
		
		Map<String, ColumnInfo> columns = tableInfo.getColumns();
		List<JavaFieldGetSet> javaFieldGetSets = new ArrayList<JavaFieldGetSet>();
		
		for( ColumnInfo c:columns.values()){
			JavaFieldGetSet j = createFieldGetSetSRC(c, convertor);
			javaFieldGetSets.add(j);
		}
		
		StringBuilder javaSrc = new StringBuilder();
		
		//生成package语句
		javaSrc.append("package" + BLANK + DBManager.getConf().getPoPackage()+";\n\n");
		//生成import语句
		javaSrc.append("import java.sql.*;\n");
		javaSrc.append("import java.util.*;\n\n");
		//生成类声明语句
		javaSrc.append("public class" + BLANK 
				+ StringUtils.firstChar2UpperCase(tableInfo.getTname()) + "{\n\n");
		//生成属性列表
		Iterator<JavaFieldGetSet> iter = javaFieldGetSets.iterator();
		while(iter.hasNext()){
			String fieldInfo = iter.next().getFieldInfo();
			javaSrc.append(fieldInfo + "\n");
		}
		javaSrc.append("\n");
		//生成get方法
		for(JavaFieldGetSet j:javaFieldGetSets){
			javaSrc.append(j.getGetInfo() + "\n");
		}
		javaSrc.append("\n");
		//生成set方法
		for(JavaFieldGetSet j:javaFieldGetSets){
			javaSrc.append(j.getSetInfo() + "\n");
		}
		javaSrc.append("\n");
		//生成类结束
		javaSrc.append("}");
		
		return javaSrc.toString();
	}

	/**
	 * 根据表结构，生成po包下对应的类
	 * @param tableInfo
	 * @param convertor
	 */
	public static void createJavaPOFile(TableInfo tableInfo, TypeConvertor convertor){
		// xxx.xxx --->xxx/xxx
		String javaSrc = JavaFileUtils.createJavaSrc(tableInfo, convertor); 
		String poPackage = DBManager.getConf().getPoPackage();
		String poPath = poPackage.replaceAll("\\.", "/");
			
		String filePath = DBManager.getConf().getSrcPath()  + "\\" + poPath + "\\" 
					+ StringUtils.firstChar2UpperCase(tableInfo.getTname()) + ".java";
		
		//System.out.println(filePath) ;
		
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(new File(filePath)));
			bw.write(javaSrc);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public static void main(String[] args) {
		/*
	// 测试createFieldGetSetSRC 方法
		ColumnInfo ci = new ColumnInfo("id", "tinyint", 1) ;
		TypeConvertor tc = new MySqlTypeConvertor() ;
		JavaFieldGetSet result1 = JavaFileUtils.createFieldGetSetSRC(ci, tc);
		System.out.println(result1);
		*/
		
	//测试createJavaSrc方法
		Map<String, ColumnInfo> columns = new HashMap<String, ColumnInfo>();
		columns.put("id", new ColumnInfo("id", "tinyint", 0));
		columns.put("name", new ColumnInfo("name", "varchar", 0));
		
		TableInfo ti = new TableInfo("user", null, columns);
		String result2 = JavaFileUtils.createJavaSrc(ti, new MySqlTypeConvertor());
		System.out.println(result2);
		
	//测试createJavaPOFile方法
		JavaFileUtils.createJavaPOFile(ti, new MySqlTypeConvertor());
	}
		
}
