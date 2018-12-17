package demo4_AIO;

import java.io.IOException;
import java.net.Socket;

public class OSHandlerAccpter implements Runnable{
	OSHandler osStrategy = new OSAcceptHandler();
	AsynServerHandler serverHandler;
	private AsynServerSocketChannel channel;
	private Handler<AsynSocketChannel, AsynServerHandler> handler;
	
	public void register(Handler<AsynSocketChannel, AsynServerHandler> handler){ //�������񣨻ص�������
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
		
		
		//���ӳɹ�����ص�������
		handler.completed(asynSocketChannel, serverHandler);
	}
	
	
}
