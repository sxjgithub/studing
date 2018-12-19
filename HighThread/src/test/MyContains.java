package test;

import java.util.concurrent.TimeUnit;

public class MyContains {
	public static void main(String[] args) {
		MyContains m = new MyContains();
		
		for(int i = 0; i < 100; i++){
			new Thread(()->{
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println(Thread.currentThread().getName() 
						+ " 消费了 " + m.get());
		
			},"c" + i).start();
		}
		
		for(int i = 0; i < 10; i++){
			new Thread(()->{
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				m.put("a");
				System.out.println(Thread.currentThread().getName() 
						+ " 生产了 ");
		
			},"c" + i).start();
		}
	}
	
	private Object[] objs;
	volatile private int count;
	private final static int MAX = 10;
	
	public MyContains() {
		count = 0;
		objs = new Object[10];
	}
	
	public  int getCount(){
		return count;
	}
	public synchronized Object get(){
		
		while(count <= 0){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		count --;
		this.notifyAll();
		System.out.println(this);
		return this.objs[count];
		
		
	
	}
	
	public synchronized void put(Object obj){
		
		while(count >= MAX){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.objs[count] = obj;
		count ++;
		this.notifyAll();
	}
	
}
