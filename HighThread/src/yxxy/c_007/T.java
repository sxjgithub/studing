/**
 * 同步和非同步方法是否可以同时调用？
 * @author mashibing
 */

package yxxy.c_007;

import java.util.LinkedList;

public class T {

	public synchronized void m1() { 
		System.out.println(Thread.currentThread().getName() + " m1 start...");
		try {
			Thread.sleep(3000); //睡3秒
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " m1 end");
	}
	
	public void m2() {
		try {
			Thread.sleep(1000); //睡一秒
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " m2:非同步方法 ");
	}
	
	public static void main(String[] args) {
		T t = new T();
		
		/*new Thread(()->t.m1(), "t1").start();
		new Thread(()->t.m2(), "t2").start();*/
		
		new Thread(t::m1, "t1").start(); 
		new Thread(t::m2, "t2").start(); 
	
		
		
		/*
		new Thread(new Runnable() {

			@Override
			public void run() {
				t.m1();
			}
			
		});
		*/
		
	}
	
}
