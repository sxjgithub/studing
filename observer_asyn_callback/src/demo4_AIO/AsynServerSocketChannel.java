package demo4_AIO;
/**
 * ���ͺ�handler�Ľ����Ҫ�Ż���
 */
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class AsynServerSocketChannel {
	public static ServerSocket serverSocket;
	public static AsynServerSocketChannel asynServerSocketChannel =
			new AsynServerSocketChannel();
	
	private static OSHandlerAccpter os = new OSHandlerAccpter();
	
//˽�л��޲ι���	
	private AsynServerSocketChannel(){} 
	
//���൥��
	public static AsynServerSocketChannel open(){
		try {
			serverSocket = new ServerSocket();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return asynServerSocketChannel;
	}
	
	public void bind(int port){
		try {
			serverSocket.bind(new InetSocketAddress("127.0.0.1", port));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void accept(AsynServerHandler serverHandler, Handler<AsynSocketChannel, AsynServerHandler> handler){
		
		//os��һ��Runnable
		//��server.accpet()����OS�߳���,ͬʱע�������OS
		/*OS implements Rnnable{
			Handler handler2;
			
			OS(channel,){
				this.channel = channel;
			}
			run(){
				handler2.completed(channel, AsynServerSocketChannel);
			}
		}
		
		try {
			Socket socket = channel.serverSocket.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		os.register(handler);
		os.getChannel(serverHandler.channel.asynServerSocketChannel);
		os.getServerHandler(serverHandler);
		new Thread(os).start();
		
		
		
	}
	
	
}
