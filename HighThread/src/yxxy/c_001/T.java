/**
 * synchronized�ؼ���
 * ��ĳ���������
 * @author mashibing
 */

package yxxy.c_001;


//˼����main�����������ʵ���̼߳��õ��������
public class T {
	
	private int count = 10;
	private Object o = new Object();
	
	public void m() {
		synchronized(o) { //�κ��߳�Ҫִ������Ĵ��룬�������õ�o����
			count--;
			System.out.println(Thread.currentThread().getName() + " count = " + count);
			
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/*public static void main(String[] args) {
		T t = new T();
		for(int i = 0; i < 5; i++){
			new Thread(()->{
				t.m();
			}).start();
		}
	}*/
}

