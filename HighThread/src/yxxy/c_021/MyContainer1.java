/**
 * �����⣺дһ���̶�����ͬ��������ӵ��put��get�������Լ�getCount������
 * �ܹ�֧��2���������߳��Լ�10���������̵߳���������
 * 
 * ʹ��wait��notify/notifyAll��ʵ��
 * 
 * @author mashibing
 */
package yxxy.c_021;

//wait  һ���  while һ��ʹ��  ��Ҫ��ifһ��ʹ��
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class MyContainer1<T> {
	final private LinkedList<T> lists = new LinkedList<>();
	final private int MAX = 10; //���10��Ԫ��
	private int count = 0;
	
	
	public synchronized T get() {
		T t = null;
		while(lists.size() == 0) { //����sizeΪ0����һ���߳�t1 ��������put�������this.wait()��
							//��size��0��Ϊ1ʱ����һ���߳�t2����������ִ����get��������ʱsizeΪ0,���ҵ���t1��������������
			try {		//�������if,��t1ֻ�����ִ��size��1������sizeΪ-1��     ����while��t1������ж�sizeʱ��Ϊ0���ᵼ�´���
				this.wait();	//�����if,����������̶������size��1��������sizeΪ1�������������while,��  
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		t = lists.removeFirst();
		count --;
		this.notifyAll(); //֪ͨ�����߽�������
		return t;
	}
	
	public synchronized void put(T t) {
		while(lists.size() == MAX) { //����Ϊʲô��while��������if��  
			try {
				this.wait(); //effective java
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		lists.add(t);
		++count;
		this.notifyAll(); //֪ͨ�������߳̽�������
	}
	
	
	
	public static void main(String[] args) {
		MyContainer1<String> c = new MyContainer1<>();
		//�����������߳�
		for(int i=0; i<10; i++) {
			new Thread(()->{
				for(int j=0; j<5; j++) System.out.println("������" + c.get());
			}, "c" + i).start();
		}
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//�����������߳�
		for(int i=0; i<2; i++) {
			new Thread(()->{
				for(int j=0; j<25; j++) c.put(Thread.currentThread().getName() + " " + j);
			}, "p" + i).start();
		}
	}
}
