package sorm.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import po.User;

/**
 * ��װ�˷���ĳ��ò���
 * @author sxj
 *
 */
public class reflectUtils {
	
	/**
	 * ����obj�����Ӧ����fieldName ��get����
	 * @param c
	 * @param fieldName
	 * @param obj
	 * @return
	 */
	public static Object getter(Object obj, String fieldName){
		try {
			Class c = obj.getClass();
			Method m = c.getMethod("get" + StringUtils.firstChar2UpperCase(fieldName), null);
					
			return m.invoke(obj, null);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void setter(Object obj, String fieldName, Class<?> type, Object fieldValue){			
		try {
			Class<?> c = obj.getClass();
			Method m = c.getMethod("set" + StringUtils.firstChar2UpperCase(fieldName), type);
			m.invoke(obj, fieldValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		User u = new User();
		reflectUtils.setter(u, "username", String.class, "sxj");
		System.out.println(reflectUtils.getter(u, "username"));
	}
}
