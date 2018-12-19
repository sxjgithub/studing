package sorm.util;

import java.sql.PreparedStatement;

import sorm.core.DBManager;

/**
 * 封装了JDBC查询常用的操作
 * @author sxj
 *
 */
public class JDBCUtils {
	
	/**
	 * 给sql设参数（前提已经执行了ps = PrepareStatement(sql)语句）
	 * @param ps 
	 * @param params 要更新的数据
	 */
	public static void handleParams(PreparedStatement ps, Object[] params){
			if(params != null){
				for(int i = 0; i < params.length; i++){
					try {
						ps.setObject(i + 1, params[i]);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
	}
	
}
