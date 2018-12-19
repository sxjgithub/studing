package Factory;

import sorm.core.DBManager;
import sorm.core.Query;

public class QueryFactory { //����ʽ��������
	 private static Query proteQuery; 
	 
	 static{
		try {
			Class<?> c = Class.forName(DBManager.getConf().getQueryFactory());
			proteQuery = (Query)c.newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	 }
	
	private QueryFactory(){} //˽�й��췽����
		
		
	public static Query getQuery(){
		return proteQuery;
	}
}
