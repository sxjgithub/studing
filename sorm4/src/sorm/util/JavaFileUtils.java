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
 * ��װ��Java�ļ���Դ���룩���õĲ���
 * @author sxj
 *
 */
public class JavaFileUtils {
	private final static String BLANK = " ";
	
	/**
	 * �����ֶ���Ϣ����java������Ϣ����varchar username--> private String username; �Լ���Ӧ��set��get����Դ��
	 * @param column �ֶ���Ϣ
	 * @param convertor ����ת����
	 * @return java���Ժ�set/get����Դ��
	 */
	public static JavaFieldGetSet createFieldGetSetSRC(ColumnInfo column, 
			TypeConvertor convertor){
		
		JavaFieldGetSet jfgs = new JavaFieldGetSet();
		
		String javaFieldType = convertor.databaseType2JavaType(column.getDataType());
		
		//����field���Դ���
		jfgs.setFieldInfo("\tprivate" + BLANK + javaFieldType + BLANK + column.getName() + ";\n");
		
		//����getter��������
		//public String getUsername(){return username;}
		StringBuilder getSrc = new StringBuilder();
		getSrc.append("\tpublic" + BLANK + javaFieldType + BLANK + 
					"get" + StringUtils.firstChar2UpperCase(column.getName()) +"()"
				+"{\n") ;
		getSrc.append("\t\treturn" + BLANK + column.getName() + ";" + "\n\t}");
		
		jfgs.setGetInfo(getSrc.toString());
		
		//����setter��������
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
	 * ���ݱ���Ϣ����java���Դ����
	 * @param tableInfo ����Ϣ
	 * @param convertor ��������ת����
	 * @return java���Դ��
	 */
	public static String createJavaSrc(TableInfo tableInfo, TypeConvertor convertor){
		
		Map<String, ColumnInfo> columns = tableInfo.getColumns();
		List<JavaFieldGetSet> javaFieldGetSets = new ArrayList<JavaFieldGetSet>();
		
		for( ColumnInfo c:columns.values()){
			JavaFieldGetSet j = createFieldGetSetSRC(c, convertor);
			javaFieldGetSets.add(j);
		}
		
		StringBuilder javaSrc = new StringBuilder();
		
		//����package���
		javaSrc.append("package" + BLANK + DBManager.getConf().getPoPackage()+";\n\n");
		//����import���
		javaSrc.append("import java.sql.*;\n");
		javaSrc.append("import java.util.*;\n\n");
		//�������������
		javaSrc.append("public class" + BLANK 
				+ StringUtils.firstChar2UpperCase(tableInfo.getTname()) + "{\n\n");
		//���������б�
		Iterator<JavaFieldGetSet> iter = javaFieldGetSets.iterator();
		while(iter.hasNext()){
			String fieldInfo = iter.next().getFieldInfo();
			javaSrc.append(fieldInfo + "\n");
		}
		javaSrc.append("\n");
		//����get����
		for(JavaFieldGetSet j:javaFieldGetSets){
			javaSrc.append(j.getGetInfo() + "\n");
		}
		javaSrc.append("\n");
		//����set����
		for(JavaFieldGetSet j:javaFieldGetSets){
			javaSrc.append(j.getSetInfo() + "\n");
		}
		javaSrc.append("\n");
		//���������
		javaSrc.append("}");
		
		return javaSrc.toString();
	}

	/**
	 * ���ݱ�ṹ������po���¶�Ӧ����
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
	// ����createFieldGetSetSRC ����
		ColumnInfo ci = new ColumnInfo("id", "tinyint", 1) ;
		TypeConvertor tc = new MySqlTypeConvertor() ;
		JavaFieldGetSet result1 = JavaFileUtils.createFieldGetSetSRC(ci, tc);
		System.out.println(result1);
		*/
		
	//����createJavaSrc����
		Map<String, ColumnInfo> columns = new HashMap<String, ColumnInfo>();
		columns.put("id", new ColumnInfo("id", "tinyint", 0));
		columns.put("name", new ColumnInfo("name", "varchar", 0));
		
		TableInfo ti = new TableInfo("user", null, columns);
		String result2 = JavaFileUtils.createJavaSrc(ti, new MySqlTypeConvertor());
		System.out.println(result2);
		
	//����createJavaPOFile����
		JavaFileUtils.createJavaPOFile(ti, new MySqlTypeConvertor());
	}
		
}
