package static_my;


/*static�������������� �� �෽������ʾ�ڴ�ֻ��һ�ݣ�
 * ���Բ���ʵ������ֱ���ã�Static_test.getVar()
 * ���ԣ�����ʹ��this��this��ʾ���󣬱���ʵ�����˲��У�ʹ��static����
 * 
 *static�����ﲻ��ʹ�÷�static��������Ϊstatic���Բ���ʵ������ֱ���ã���staticֻ��ʵ�������У�
 * */
public class Test {
	public static void main(String[] args) {
		System.out.println(Static_test.getVar()) ; //
	}
	
}

class Static_test{
	private static String var = "��ѧ��" ; //��ʾ���е��๲��һ��var������ֻ��һ��
	//private Static_test var1 = new Static_test() ;
	
	public static String getVar(){
		//Static_test v = this.var1 ;
		return var ;
		//return this.var ;
		
	}
}