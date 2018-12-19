package Factory;

import sorm.core.DBManager;
import sorm.core.Query;
import sorm.core.TableContext;

public class QueryFactory { //饿汉式单例工厂
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
	
	private QueryFactory(){} //私有构造方法；
		
		
	public static Query getQuery(){
		return proteQuery;
	}
}
