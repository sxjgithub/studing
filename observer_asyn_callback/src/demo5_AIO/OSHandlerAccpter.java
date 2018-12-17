package demo5_AIO;

import java.io.IOException;
import java.net.Socket;

public class OSHandlerAccpter<A> implements Runnable{
	OSHandler osStrategy = new OSAcceptHandler();
	AsynServerHandler serverHandler;
	private AsynServerSocketChannel channel;
	private Handler<AsynSocketChannel, ? super A> handler;
	private A attachment;
	
	public void register(Handler<AsynSocketChannel, ? super A> handler){ //接收任务（回调函数）
		this.handler = handler;
	}
	public void getChannel(AsynServerSocketChannel channel){
		this.channel = channel;
	}
	
	public void getServerHandler(AsynServerHandler serverHandler){
		this.serverHandler = serverHandler;
	}
	
	@Override
	public void run() {
		AsynSocketChannel asynSocketChannel = osStrategy.acceptHandler(channel);
		
		
		//连接成功后处理回调函数。 
		//这里泛型为什么不行？考虑反射，不用泛型-----------------------------------------------------------------------------
		handler.completed(asynSocketChannel, serverHandler); 
	}
	public <A> void getAttachment(A attachment) {
		
	}
	
	
}
