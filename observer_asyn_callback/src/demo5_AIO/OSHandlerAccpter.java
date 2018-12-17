package demo5_AIO;

import java.io.IOException;
import java.net.Socket;

public class OSHandlerAccpter<A> implements Runnable{
	OSHandler osStrategy = new OSAcceptHandler();
	AsynServerHandler serverHandler;
	private AsynServerSocketChannel channel;
	private Handler<AsynSocketChannel, ? super A> handler;
	private A attachment;
	
	public void register(Handler<AsynSocketChannel, ? super A> handler){ //�������񣨻ص�������
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
		//���ﷺ��Ϊʲô���У����Ƿ��䣬���÷���-----------------------------------------------------------------------------
		handler.completed(asynSocketChannel, serverHandler); 
	}
	public <A> void getAttachment(A attachment) {
		
	}
	
	
}
