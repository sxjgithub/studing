package sorm.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sorm.bean.Configuration;
import sorm.core.DBManager;

/**
 * 管理数据库Connection的连接池
 * @author sxj
 *
 */
public class DBConnPool {
	private static Configuration conf = DBManager.getConf();
	
	private static List<Connection> pool; //连接池对象
	
	private static final int POOL_MAX_SIZE = 100; //最大连接数
	
	private static final int POOL_MIN_SIZE = 10; //最小连接数
	
	public DBConnPool() {
		initPool();
	}
	
	
	/**
	 * 初始化连接池，连接池中数量为最小值
	 */
	public void initPool(){
		if(pool == null){
			pool = new ArrayList<Connection>();
		}
		
		while(pool.size() < POOL_MIN_SIZE ){
			Connection conn = DBManager.createConn();
			pool.add(conn);
			System.out.println("c初始化池，池中连接数：" + pool.size());
		}
		
	}
	
	/**
	 * 从连接池中取出一个连接
	 * @return
	 */
	public synchronized Connection getConnection(){
		int last_index = pool.size() - 1;
		Connection conn = pool.get(last_index);
		pool.remove(last_index);
		return conn;
	}
	
	/**
	 * 将连接放回池中
	 */
	public synchronized void close(Connection conn){
		if(pool.size() >= POOL_MAX_SIZE){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			pool.add(conn);
			System.out.println("-----------------" + pool.size());//********************************
		}
	}

}
