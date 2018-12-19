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
 * �����ѯ�������ṩ����ĺ����ࣩ
 * @author sxj
 *
 */
@SuppressWarnings("all")
public abstract class Query {
	
	private final static String BLANK = " ";
	/**
	 * ֱ��ִ��һ��DML���
	 * @param sql sql���
	 * @param param ����
	 * @return ִ��sql����Ӱ���¼������
	 */

	
	/**
	 * ִ��sql���²��������룬ɾ�������µȣ�
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
	 * ��һ������洢�����ݿ���
	 * @param obj Ҫ�洢�Ķ���
	 */
	public void insert(Object obj) throws Exception {
		//insert into user (id, username , age) values(?, ?, ?);
		Class c = obj.getClass();
		Map<Class, TableInfo> poTable = TableContext.poClassTableMap;
		TableInfo tableInfo = poTable.get(c); //���������Ӧ�ı���
		
		//��ò�Ϊ�յ����� ������ fieldList��
		
		Field[] fields =c.getDeclaredFields();
		Map<String, Object> fieldNameValues = new HashMap<String, Object>();
		for(Field field:fields){
			Object fieldValue = reflectUtils.getter(obj, field.getName());
			if(fieldValue != null){
				fieldNameValues.put(field.getName(), fieldValue);
			}
		}
		
		//����sql���
		StringBuilder sql = new StringBuilder();
		int count = 0; //��¼�м������ԣ�Ȼ���Ӧ������
		
		sql.append("insert into" + BLANK + tableInfo.getTname() + BLANK + "(");
		for(Map.Entry<String, Object> set:fieldNameValues.entrySet()){
			sql.append(set.getKey() + ",");
			count ++;
		}
		sql.setCharAt(sql.length() - 1, ')');
		sql.append(BLANK + "values(");
		
		for(int i = 0; i < count; i++){ //���sql�еģ�
			sql.append("?,");
		}
		sql.setCharAt(sql.length()-1, ')');
		
		System.out.println(sql.toString());
		
		//ִ��sql���
		Collection<Object> fieldValues =fieldNameValues.values();
		Object[] values = fieldValues.toArray();
		excuteDML(sql.toString(), values);
		
	}
	
	/**
	 * ɾ��clazz��ʾ���Ӧ�ı��еļ�¼��ָ������ֵid�ļ�¼��
	 * @param clazz �����Ӧ�����Class����
	 * @param id ������ֵ
	 */
	public void delete(Class clazz, Object id) {
		//Emp.class,2--> delete from Emp where id=2
		
		//ͨ��Class������TableInfo
		TableInfo tableInfo = TableContext.poClassTableMap.get(clazz);
		//�������
		ColumnInfo onlyPrikey = tableInfo.getOnlyPriKey();
		
		String sql = "delete from" + BLANK + tableInfo.getTname() 
				+ BLANK + "where" + BLANK + onlyPrikey.getName() + "=?" ;
		
		excuteDML(sql, new Object[]{id});
	}
	
	/**
	 * ɾ�����������ݿ��ж�Ӧ�ļ�¼�������������Ӧ�������������Ӧ��¼��
	 * @param obj ���ж�Ӧ���ݿ��¼�Ķ���
	 */
	public void delete(Object obj) {
		Class c = obj.getClass();
		TableInfo tableInfo = TableContext.poClassTableMap.get(c);
		ColumnInfo onlyPrInfo = tableInfo.getOnlyPriKey(); //����
		
		//ͨ������������Զ�Ӧ��getter����setter
		Object priKeyValue = reflectUtils.getter(obj, onlyPrInfo.getName());
		
		delete(c, priKeyValue);
		
	}
	
	/**
	 * ���¶����Ӧ�ļ�¼������ֻ����ָ�����ֶε�ֵ
	 * @param obj ��Ҫ���µĶ���
	 * @param fieldNames ���µ������б�
	 * @return ִ��sql����Ӱ���¼������
	 */
	public int update(Object obj, String[] fieldNames) { //ע�⣬obj�������ú������������������ݿ����ҵ��ö���ļ�¼�������޸�
		// fieldName{username, password} ---> update user set username=?, password=?  where id=3;
		
		Class c = obj.getClass();
		Map<Class, TableInfo> poTable = TableContext.poClassTableMap;
		TableInfo tableInfo = poTable.get(c); //ȡ�ñ���Ϣ
		String tableName = tableInfo.getTname();
		Map<String, Object> fieldNameValues = new HashMap<>();
		ColumnInfo priKey = tableInfo.getOnlyPriKey(); //�������
		Object priKeyValue = reflectUtils.getter(obj, priKey.getName()); //���������Ӧ��ֵ �磺id=3
		
		for(int i = 0; i < fieldNames.length; i++){//��ö����Ҫ�������Ե����ֺ�ֵ������fieldNameValues��
			Object fieldValue = reflectUtils.getter(obj, fieldNames[i]);
			fieldNameValues.put(fieldNames[i], fieldValue); 
		}
		
		
		//����sql���
		StringBuilder sql = new StringBuilder();
		sql.append("update" + BLANK + tableName + BLANK 
				+ "set" + BLANK);
		for(Map.Entry<String, Object> entry:fieldNameValues.entrySet()){
			sql.append(entry.getKey() + "=?,");
		}
		sql.setCharAt(sql.length()-1, ' '); //�����һ�����Ż��ɿո�  update user set username=?, password=?,
										//----->update user set username=?, password=?
		sql.append(BLANK + "where" + BLANK + priKey.getName() + "=" + priKeyValue);
		
		Collection<Object> fieldValues = fieldNameValues.values();
		Object[] valuesArrays = fieldValues.toArray();
		return excuteDML(sql.toString(), valuesArrays);
		
		
	}
	
	/**
	 * ��ѯ���ض��м�¼������ÿ�м�¼��װ��clazzָ������Ķ�����
	 * @param sql ��ѯ���
	 * @param clazz ��װ���ݵ�javabean���Class����
	 * @param params sql�Ĳ���
	 * @return ��ѯ���Ľ��
	 */
	public List queryRows(String sql, Class clazz, Object[] params) {//Ŀǰֻ�ܲ�ѯselect * ,
																//�����Ľ����Բ�ѯ��������select id,age
		//sql = select * from user where id>?, age>?  params={2,10};
		
		List<Object> objects = new ArrayList<>(); //�洢��ѯ���ļ�¼
		Field[] fields = clazz.getDeclaredFields();
		List<Class<?>> fieldTypes = new ArrayList<>();
		List<String> fieldNames = new ArrayList<String>();
		for(int i = 0; i < fields.length; i++){ //��clazz����������Ƽ��ص�fieldNames�б���
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
				Object obj = clazz.newInstance();  //����newһ��clazz����
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
	 * ��ѯ����һ�м�¼������ÿ�м�¼��װ��clazzָ������Ķ�����
	 * @param sql ��ѯ���
	 * @param clazz ��װ���ݵ�javabean���Class����
	 * @param params sql�Ĳ���
	 * @return ��ѯ���Ľ��
	 */
	public Object queryUniqueRow(String sql, Class clazz, Object[] params) {
		return queryRows(sql, clazz, params).get(0);
		
	}
	/**
	 * ��ѯ����һ��ֵ��һ��һ�У���������ֵ����
	 * @param sql ��ѯ���
	 * @param params sql�Ĳ���
	 * @return ��ѯ���Ľ��
	 */
	public Object queryValue(String sql, Object[] params) {
		// sql = "select count(*) from user where id>?"  params={2}
		return null;
	}
	
	/**
	 * ��ѯ����һ����ֵ��һ��һ�У���������ֵ����
	 * @param sql ��ѯ���
	 * @param params sql�Ĳ���
	 * @return ��ѯ���Ľ��
	 */
	public Number queryNumber(String sql, Object[] params) {
		return (Number)queryValue(sql, params);
	}
	
	
	public static void main(String[] args) {
		MySqlQuery mq = new MySqlQuery();
		User u = new User();
		u.setId(6);
		u.setUsername("����");
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
