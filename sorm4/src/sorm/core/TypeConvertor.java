package sorm.core;

/**
 * ����java�������ͺ����ݿ����͵Ļ���ת��
 * @author Administrator
 *
 */
public interface TypeConvertor {
	
	/**
	 *  �����ݿ���������ת����java����������
	 * @param columnType
	 * @return
	 */
	public String databaseType2JavaType(String columnType);
	
	/**
	 *  ��java����������ת�������ݿ���������
	 * @param columnType
	 * @return 
	 */
	public String JavaType2databaseType(String columnType);
}
