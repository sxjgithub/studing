package demo2;
import java.util.concurrent.TimeUnit;

public class Client {
	OS os = new OS();
	public static void main(String[] args) {
		Client client = new Client();
		
		
		MyCallBack myCallBack = new MyCallBack();
		client.DispatherTask2OS(myCallBack);
		
		client.asyDoOthers(); //异步干其他的事情
		//client.idDone();
		System.out.println(myCallBack.getFlag());
		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(myCallBack.getFlag());
	}

	public void asyDoOthers() {
		System.out.println("哈哈，我不用等了，异步干其他事情");
		
	}
	
	
	//问题1：不同参数怎么传，怎么设计一个统一的DispatherTask2OS()方法-----把参数也封装下
	//问题2：怎么获得OS的处理状态，处理数据的状态
	//问题2：怎么多个
	public void DispatherTask2OS(CallBack callBack) {
		//OS怎样在这个方法 接收并处理回调函数	
		os.register(callBack);
		new Thread(os).start(); //注册任务(回调函数),并让OS执行任务
		
		//注册后OS把回调函数加入队列  ，然后取出任务执行。
		//new Thread(()->new OS().update(x, y,callBack)).start();
		
	}
	
}
