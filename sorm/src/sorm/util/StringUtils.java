package sorm.util;


/**
 * ��װ��String���õĲ���
 * @author sxj
 *
 */
public class StringUtils {
	
	/**
	 * ��Ŀ���ַ�������ĸ��д
	 * @param str
	 * @return 
	 */
	public static String firstChar2UpperCase(String str){
		if(str == null || "".equalsIgnoreCase(str) || !str.matches("[a-zA-Z].*")){
			return null ;
		}
		return str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
	}
	
	public static void main(String[] args) {
		System.out.println(StringUtils.firstChar2UpperCase("a"));
		System.out.println(StringUtils.firstChar2UpperCase("Abgd"));
		System.out.println(StringUtils.firstChar2UpperCase(""));
		System.out.println(StringUtils.firstChar2UpperCase("1"));
		System.out.println(StringUtils.firstChar2UpperCase(null));
	}
}
