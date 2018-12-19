package single_my;

import sun.security.jca.GetInstance;

public class Single_test {
	public static void main(String[] args) {
		//new Single() ;
		Single1 s1 =Single1.getInstance() ;
		s1.print();
		
		Single2 s2 = Single2.getInstance() ;
		s2.print();
		
	}
}

//����ģʽ1
/*����:��1��Ҫ��ʵ�ֵ��������������new�ˣ��Ͱѹ��캯��˽�л���
 * 		��2���������Ͳ���ʵ�����ˣ������������������ù��캯��ʾ������private Single INSTANCE = new Single();
 * 			class Single{
					private Single INSTANCE = new Single();
					private Single() {}
			}

 * 		��3����������ô��������ʹ������࣬���������� Single.getInstance() ���ַ�����getInstance �ͱ�����static������
 * 				INSTANCEҲֻ����һ�ݣ�ҲҪ��static�� ͬʱINSTANCE ���ܳ�ʼ�����ܸı䣬���м���final��
 * */
class Single1{
	private static final Single1 INSTANCE = new Single1();
	private Single1() {}
	public static Single1 getInstance(){
		return INSTANCE ;
	}
	public void print(){
		System.out.println(INSTANCE) ;
	}
}

//����ģʽ2
class Single2{
	private static Single2 single ;
	private Single2(){}
//�жϣ�ͨ��getInstance������ʼ�� Single2��	
	public static Single2 getInstance(){
		if(null == single){
			single = new Single2() ;
		}
		return single ;
	}
	public void print(){
		System.out.println(single) ;
	}
}