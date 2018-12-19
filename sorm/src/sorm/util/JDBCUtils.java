package sorm.util;

import java.sql.PreparedStatement;

import sorm.core.DBManager;

/**
 * ��װ��JDBC��ѯ���õĲ���
 * @author sxj
 *
 */
public class JDBCUtils {
	
	/**
	 * ��sql�������ǰ���Ѿ�ִ����ps = PrepareStatement(sql)��䣩
	 * @param ps 
	 * @param params Ҫ���µ�����
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
