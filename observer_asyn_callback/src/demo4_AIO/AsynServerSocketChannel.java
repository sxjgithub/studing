package demo4_AIO;
/**
 * 泛型和handler的结合需要优化，
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
	
//私有化无参构造	
	private AsynServerSocketChannel(){} 
	
//本类单例
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
		
		//os是一个Runnable
		//把server.accpet()放在OS线程中,同时注册任务给OS
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
