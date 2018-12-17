package demo3;

import java.util.concurrent.TimeUnit;

public class Test {
	public static void main(String[] args) {
		AsynClient client = new AsynClient();
		
		
		MyCallBack myCallBack = new MyCallBack(); //制定一个任务
		client.DispatherTask2OS(myCallBack);  //提交任务
		
		client.asyDoOthers(); //异步干其他的事情

		System.out.println(myCallBack.isDone()); //查看任务是否完成
		
		MyCallBack myCallBack2 = new MyCallBack(); //制定一个任务
		client.DispatherTask2OS(myCallBack2);  //提交任务
		client.asyDoOthers(); //异步干其他的事情
		
		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(myCallBack.getFlag()); //getFlag方法被设置为protected了为什么还能够在其他类里面使用？
		
		client.close();
	}
}
