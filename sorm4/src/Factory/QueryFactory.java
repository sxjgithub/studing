package Factory;

import sorm.core.DBManager;
import sorm.core.Query;
import sorm.core.TableContext;

public class QueryFactory { //����ʽ��������
	 private static Query proteQuery; 
	 
	 static{
		try {
			Class<?> c = Class.forName(DBManager.getConf().getQueryFactory());
			proteQuery = (Query)c.newInstance();
			TableContext.start();
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
