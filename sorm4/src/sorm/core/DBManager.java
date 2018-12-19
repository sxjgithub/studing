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
 * 根据配置信息，维持连接对象的管理（增加连接池功能）
 * @author sxj
 *
 */
public class DBManager {
	
	private static Configuration conf;
	private static DBConnPool dbConnPool = null;
	static{ //静态代码块    静态代码块在类初始化的时候执行一次
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
	 * 创建新的Connection对象
	 * @return
	 */
	public static Connection createConn(){
		
		try {
			Class.forName(conf.getDriver());
			return DriverManager.getConnection(conf.getUrl(), 
					conf.getUser(), conf.getPwd()); //目前直接建立连接，后期增加连接池处理，提高效率
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
