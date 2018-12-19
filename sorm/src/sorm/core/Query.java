package sorm.core;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import po.User;
import sorm.bean.ColumnInfo;
import sorm.bean.TableInfo;
import sorm.util.JDBCUtils;
import sorm.util.reflectUtils;

/**
 * 负责查询（对外提供服务的核心类）
 * @author sxj
 *
 */
@SuppressWarnings("all")
public abstract class Query {
	
	private final static String BLANK = " ";
	/**
	 * 直接执行一个DML语句
	 * @param sql sql语句
	 * @param param 参数
	 * @return 执行sql语句后影响记录的行数
	 */

	
	/**
	 * 执行sql更新操作（插入，删除，更新等）
	 */
	public int excuteDML(String sql, Object[] params) {
		Connection conn = DBManager.getConn();
		int count = 0;
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			JDBCUtils.handleParams(ps, params);
			count = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBManager.close(ps, conn);
		}
		return count;
	}
	
	/**
	 * 将一个对象存储到数据库中
	 * @param obj 要存储的对象
	 */
	public void insert(Object obj) throws Exception {
		//insert into user (id, username , age) values(?, ?, ?);
		Class c = obj.getClass();
		Map<Class, TableInfo> poTable = TableContext.poClassTableMap;
		TableInfo tableInfo = poTable.get(c); //获得类名对应的表名
		
		//获得不为空的属性 保存在 fieldList中
		
		Field[] fields =c.getDeclaredFields();
		Map<String, Object> fieldNameValues = new HashMap<String, Object>();
		for(Field field:fields){
			Object fieldValue = reflectUtils.getter(obj, field.getName());
			if(fieldValue != null){
				fieldNameValues.put(field.getName(), fieldValue);
			}
		}
		
		//构建sql语句
		StringBuilder sql = new StringBuilder();
		int count = 0; //记录有几个属性，然后对应几个？
		
		sql.append("insert into" + BLANK + tableInfo.getTname() + BLANK + "(");
		for(Map.Entry<String, Object> set:fieldNameValues.entrySet()){
			sql.append(set.getKey() + ",");
			count ++;
		}
		sql.setCharAt(sql.length() - 1, ')');
		sql.append(BLANK + "values(");
		
		for(int i = 0; i < count; i++){ //添加sql中的？
			sql.append("?,");
		}
		sql.setCharAt(sql.length()-1, ')');
		
		System.out.println(sql.toString());
		
		//执行sql语句
		Collection<Object> fieldValues =fieldNameValues.values();
		Object[] values = fieldValues.toArray();
		excuteDML(sql.toString(), values);
		
	}
	
	/**
	 * 删除clazz表示类对应的表中的记录（指定主键值id的记录）
	 * @param clazz 跟表对应的类的Class对象
	 * @param id 主键的值
	 */
	public void delete(Class clazz, Object id) {
		//Emp.class,2--> delete from Emp where id=2
		
		//通过Class对象找TableInfo
		TableInfo tableInfo = TableContext.poClassTableMap.get(clazz);
		//获得主键
		ColumnInfo onlyPrikey = tableInfo.getOnlyPriKey();
		
		String sql = "delete from" + BLANK + tableInfo.getTname() 
				+ BLANK + "where" + BLANK + onlyPrikey.getName() + "=?" ;
		
		excuteDML(sql, new Object[]{id});
	}
	
	/**
	 * 删除对象在数据库中对应的记录（对象所在类对应表，对象的主键对应记录）
	 * @param obj 类中对应数据库记录的对象
	 */
	public void delete(Object obj) {
		Class c = obj.getClass();
		TableInfo tableInfo = TableContext.poClassTableMap.get(c);
		ColumnInfo onlyPrInfo = tableInfo.getOnlyPriKey(); //主键
		
		//通过反射调用属性对应的getter或者setter
		Object priKeyValue = reflectUtils.getter(obj, onlyPrInfo.getName());
		
		delete(c, priKeyValue);
		
	}
	
	/**
	 * 更新对象对应的记录，并且只更新指定的字段的值
	 * @param obj 所要更新的对象
	 * @param fieldNames 更新的属性列表
	 * @return 执行sql语句后影响记录的行数
	 */
	public int update(Object obj, String[] fieldNames) { //注意，obj必须设置好了主键，才能在数据库中找到该对象的记录，在做修改
		// fieldName{username, password} ---> update user set username=?, password=?  where id=3;
		
		Class c = obj.getClass();
		Map<Class, TableInfo> poTable = TableContext.poClassTableMap;
		TableInfo tableInfo = poTable.get(c); //取得表信息
		String tableName = tableInfo.getTname();
		Map<String, Object> fieldNameValues = new HashMap<>();
		ColumnInfo priKey = tableInfo.getOnlyPriKey(); //获得主键
		Object priKeyValue = reflectUtils.getter(obj, priKey.getName()); //获得主键对应的值 如：id=3
		
		for(int i = 0; i < fieldNames.length; i++){//获得对象的要更新属性的名字和值，放在fieldNameValues里
			Object fieldValue = reflectUtils.getter(obj, fieldNames[i]);
			fieldNameValues.put(fieldNames[i], fieldValue); 
		}
		
		
		//构建sql语句
		StringBuilder sql = new StringBuilder();
		sql.append("update" + BLANK + tableName + BLANK 
				+ "set" + BLANK);
		for(Map.Entry<String, Object> entry:fieldNameValues.entrySet()){
			sql.append(entry.getKey() + "=?,");
		}
		sql.setCharAt(sql.length()-1, ' '); //把最后一个逗号换成空格  update user set username=?, password=?,
										//----->update user set username=?, password=?
		sql.append(BLANK + "where" + BLANK + priKey.getName() + "=" + priKeyValue);
		
		Collection<Object> fieldValues = fieldNameValues.values();
		Object[] valuesArrays = fieldValues.toArray();
		return excuteDML(sql.toString(), valuesArrays);
		
		
	}
	
	/**
	 * 查询返回多行记录，并将每行记录封装到clazz指定的类的对象中
	 * @param sql 查询语句
	 * @param clazz 封装数据的javabean类的Class对象
	 * @param params sql的参数
	 * @return 查询到的结果
	 */
	public List queryRows(String sql, Class clazz, Object[] params) {//目前只能查询select * ,
																//后续改进可以查询部分属性select id,age
		//sql = select * from user where id>?, age>?  params={2,10};
		
		List<Object> objects = new ArrayList<>(); //存储查询到的记录
		Field[] fields = clazz.getDeclaredFields();
		List<Class<?>> fieldTypes = new ArrayList<>();
		List<String> fieldNames = new ArrayList<String>();
		for(int i = 0; i < fields.length; i++){ //把clazz类的属性名称加载到fieldNames列表中
			fieldNames.add(fields[i].getName());
			fieldTypes.add(fields[i].getType());
		}
		
		Connection conn = DBManager.getConn();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			JDBCUtils.handleParams(ps, params);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				
				int count = 0;
				Object obj = clazz.newInstance();  //反射new一个clazz对象
				for(int i = 0; i < fields.length; i++){
					reflectUtils.setter(obj, fieldNames.get(count),fieldTypes.get(count), 
							rs.getObject(fieldNames.get(count)));
					count ++;
				}
				objects.add(obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBManager.close(ps, conn);
		}
		return objects;
	}
	
	/**
	 * 查询返回一行记录，并将每行记录封装到clazz指定的类的对象中
	 * @param sql 查询语句
	 * @param clazz 封装数据的javabean类的Class对象
	 * @param params sql的参数
	 * @return 查询到的结果
	 */
	public Object queryUniqueRow(String sql, Class clazz, Object[] params) {
		return queryRows(sql, clazz, params).get(0);
		
	}
	/**
	 * 查询返回一个值（一行一列），并将该值返回
	 * @param sql 查询语句
	 * @param params sql的参数
	 * @return 查询到的结果
	 */
	public Object queryValue(String sql, Object[] params) {
		// sql = "select count(*) from user where id>?"  params={2}
		return null;
	}
	
	/**
	 * 查询返回一个数值（一行一列），并将该值返回
	 * @param sql 查询语句
	 * @param params sql的参数
	 * @return 查询到的结果
	 */
	public Number queryNumber(String sql, Object[] params) {
		return (Number)queryValue(sql, params);
	}
	
	
	public static void main(String[] args) {
		MySqlQuery mq = new MySqlQuery();
		User u = new User();
		u.setId(6);
		u.setUsername("王八");
		u.setPassword("456");
		u.setAge(1000);
		//mq.delete(u);
		
		/*try {
			mq.insert(u);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//mq.update(u, new String[]{"username", "password", "age"});
		
		//List<User> users = mq.queryRows("select username from user where age>?", User.class, new Object[]{10});
		List<User> users = mq.queryRows("select * from user where age>?", User.class, new Object[]{10});
		for(User user: users){
			System.out.println(user);
		}
	}
}
