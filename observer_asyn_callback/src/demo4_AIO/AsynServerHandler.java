package demo4_AIO;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class AsynServerHandler implements Runnable {
	public CountDownLatch latch;
	public AsynServerSocketChannel channel;
	public AsynServerHandler(int port) {
		try {
			//创建服务端通道
			channel = AsynServerSocketChannel.open();
			//绑定端口
			channel.bind(port);
			System.out.println("服务器已启动，端口号：" + port);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		//CountDownLatch初始化
		//它的作用：在完成一组正在执行的操作之前，允许当前的现场一直阻塞
		//此处，让现场在此阻塞，防止服务端执行完成后退出
		//也可以使用while(true)+sleep 
		//生成环境就不需要担心这个问题，以为服务端是不会退出的
		latch = new CountDownLatch(1);
		//用于接收客户端的连接
		channel.accept(this,new AcceptHandler());
		System.out.println("我是服务器，哈哈，我可以异步了");
		try {
			TimeUnit.SECONDS.sleep(7);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("我是服务器，哈哈，我可以异步了");
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

