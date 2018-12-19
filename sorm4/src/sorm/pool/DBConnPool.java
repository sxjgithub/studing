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
 * �������ݿ�Connection�����ӳ�
 * @author sxj
 *
 */
public class DBConnPool {
	private static Configuration conf = DBManager.getConf();
	
	private static List<Connection> pool; //���ӳض���
	
	private static final int POOL_MAX_SIZE = 100; //���������
	
	private static final int POOL_MIN_SIZE = 10; //��С������
	
	public DBConnPool() {
		initPool();
	}
	
	
	/**
	 * ��ʼ�����ӳأ����ӳ�������Ϊ��Сֵ
	 */
	public void initPool(){
		if(pool == null){
			pool = new ArrayList<Connection>();
		}
		
		while(pool.size() < POOL_MIN_SIZE ){
			Connection conn = DBManager.createConn();
			pool.add(conn);
			System.out.println("c��ʼ���أ�������������" + pool.size());
		}
		
	}
	
	/**
	 * �����ӳ���ȡ��һ������
	 * @return
	 */
	public synchronized Connection getConnection(){
		int last_index = pool.size() - 1;
		Connection conn = pool.get(last_index);
		pool.remove(last_index);
		return conn;
	}
	
	/**
	 * �����ӷŻس���
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
