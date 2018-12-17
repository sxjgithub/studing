package demo1;
import java.util.concurrent.TimeUnit;

public class Client {
	OS os = new OS();
	public static void main(String[] args) {
		Client client = new Client();
		
		
		//os.register(client); // client注册到OS中
		
		client.DispatherTask2OS(3, 5, new CallBack() { //提交任务
			
			@Override
			public void doExcute(int x, int y) {
				
				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("结果为:" + (x + y));
				
			}
		});
		
		client.asyDoOthers(); //异步干其他的事情
		client.idDone();
	}

	public void asyDoOthers() {
		System.out.println("哈哈，我不用等了，异步干其他事情");
		
	}
	
	
	//问题1：不同参数怎么传，怎么设计一个统一的DispatherTask2OS()方法-----把参数也封装下
	//问题2：怎么获得OS的处理状态，处理数据的状态
	//问题2：怎么多个
	public void DispatherTask2OS(int x ,int y,CallBack callBack) {
		//OS怎样在这个方法 接收并处理回调函数	
		new Thread(()->os.register(x, y, this, callBack)).start(); //注册任务(回调函数),并让OS执行任务
		
		//注册后OS把回调函数加入队列  ，然后取出任务执行。
		//new Thread(()->new OS().update(x, y,callBack)).start();
		
	}
	
}
