/**
 * synchronized关键字
 * 对某个类加锁
 * 
 * 程序编译后会生成唯一类对象，  所有类也是一个对象，且唯一
 * @author mashibing
 */

package yxxy.c_004;

import javax.activation.MailcapCommandMap;

public class T {

	private static int count = 10;
	
	public synchronized static void m() { //这里等同于synchronized(yxxy.c_004.T.class)
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
		synchronized(T.class) { //考虑一下这里写synchronized(this)是否可以？
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
