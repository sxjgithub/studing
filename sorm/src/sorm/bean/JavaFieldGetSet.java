package sorm.bean;

/**
 * ��װ��java���Ժ�get��set������Դ����
 * @author sxj
 *
 */
public class JavaFieldGetSet {
	
	/**
	 * ���Ե�Դ����Ϣ���磺private int userId;
	 */
	private String fieldInfo;
	
	/**
	 * get������Դ����Ϣ���磺public int getUserId(){ return userId;}
	 */
	private String getInfo;
	
	/**
	 * set������Դ����Ϣ���磺public void setUserId(int id){
	 * 						this.userId = id;
	 * 					}
	 */
	private String setInfo;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "fieldInfo:\n" + getFieldInfo() + "\n\n" 
				+"getter:\n" + getGetInfo() + "\n\n"
				+"setter:\n" + getSetInfo() ;
	}

	public String getFieldInfo() {
		return fieldInfo;
	}

	public void setFieldInfo(String fieldInfo) {
		this.fieldInfo = fieldInfo;
	}

	public String getGetInfo() {
		return getInfo;
	}

	public void setGetInfo(String getInfo) {
		this.getInfo = getInfo;
	}

	public String getSetInfo() {
		return setInfo;
	}

	public void setSetInfo(String setInfo) {
		this.setInfo = setInfo;
	}

	public JavaFieldGetSet(String fieldInfo, String getInfo, String setInfo) {
		super();
		this.fieldInfo = fieldInfo;
		this.getInfo = getInfo;
		this.setInfo = setInfo;
	}
	
	public JavaFieldGetSet() {
		// TODO Auto-generated constructor stub
	}
}
