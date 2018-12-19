/**
 * synchronized�ؼ���
 * ��ĳ�������
 * 
 * �������������Ψһ�����  ������Ҳ��һ��������Ψһ
 * @author mashibing
 */

package yxxy.c_004;

import javax.activation.MailcapCommandMap;

public class T {

	private static int count = 10;
	
	public synchronized static void m() { //�����ͬ��synchronized(yxxy.c_004.T.class)
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void mm() {
		synchronized(T.class) { //����һ������дsynchronized(this)�Ƿ���ԣ�
			count --;
			System.out.println(Thread.currentThread().getName() + " count = " + count);
			
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		for(int i = 0; i < 5; i ++){
			new Thread(()->{
				T.m();
			}).start();
		}
	}

}
