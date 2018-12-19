package sorm.core;

/**
 * mysql数据类型和java数据类型的转换
 * @author sxj
 *
 */
public class MySqlTypeConvertor implements TypeConvertor{

	@Override
	public String databaseType2JavaType(String columnType) {
		if("varchar".equalsIgnoreCase(columnType) || "char".equalsIgnoreCase(columnType)){
			return "String";
		}else if("int".equalsIgnoreCase(columnType) 
				|| "tinyint".equalsIgnoreCase(columnType)
				|| "integer".equalsIgnoreCase(columnType)){
			return "Integer";
		}else if("bigint".equalsIgnoreCase(columnType)){
			return "long";
		}else if("double".equalsIgnoreCase(columnType)
				|| "float".equalsIgnoreCase(columnType)){
			return "Double";
		}else if("date".equalsIgnoreCase(columnType)){
			return "java.sql.Date";
		}
		return null;
	}

	@Override
	public String JavaType2databaseType(String columnType) {
		// TODO Auto-generated method stub
		return null;
	}

}
