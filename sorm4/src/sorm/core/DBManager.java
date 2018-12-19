package sorm.core;



import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import sorm.bean.Configuration;
import sorm.pool.DBConnPool;

/**
 * ����������Ϣ��ά�����Ӷ���Ĺ����������ӳع��ܣ�
 * @author sxj
 *
 */
public class DBManager {
	
	private static Configuration conf;
	private static DBConnPool dbConnPool = null;
	static{ //��̬�����    ��̬����������ʼ����ʱ��ִ��һ��
		Properties props = new Properties();
		try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		conf = new Configuration();
		conf.setDriver(props.getProperty("driver"));
		conf.setUrl(props.getProperty("url"));
		conf.setUser(props.getProperty("user"));
		conf.setPwd(props.getProperty("pwd"));
		conf.setPoPackage(props.getProperty("poPackage"));
		conf.setUsingDB(props.getProperty("usingDB"));
		conf.setSrcPath(props.getProperty("srcPath"));
		conf.setQueryFactory(props.getProperty("queryFactory"));
		
		
	}
	
	
	public static Configuration getConf(){
		return  conf;
	}
	
	
	
	
	public static Connection getConn(){
		if(dbConnPool == null){
			dbConnPool = new DBConnPool();
		}
		return dbConnPool.getConnection();
		
	}
	/**
	 * �����µ�Connection����
	 * @return
	 */
	public static Connection createConn(){
		
		try {
			Class.forName(conf.getDriver());
			return DriverManager.getConnection(conf.getUrl(), 
					conf.getUser(), conf.getPwd()); //Ŀǰֱ�ӽ������ӣ������������ӳش������Ч��
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static void close(ResultSet rs, Statement ps, Connection conn){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
			}
		}
		if(ps != null){
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		dbConnPool.close(conn);
	}
	public static void close(Statement ps, Connection conn){
		if(ps != null){
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		dbConnPool.close(conn);
	}
	public static void close(Connection conn){
		
		dbConnPool.close(conn);
	}
}
