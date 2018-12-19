package demo6;
/*此版本加入多线程，把每一个客户端放在一个线程里
功能：接收request数据（并分析其中的参数，method等），根据request 返回相应的response
 * */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.sun.prism.paint.Stop;


public class HttpServer {
	private ServerSocket server ;
	public static final String CRLF = "\r\n" ;
	public static final String BLANK = " " ;
	private boolean isShutDown = false ;
	
	public static void main(String[] args) {
		HttpServer httpServer = new HttpServer() ;
		httpServer.start();
	}
	
	public void start(){
		start(8888);
	}
	public void start(int port){
		try {
			 server = new ServerSocket(port) ;
			 this.receive();
			 
		} catch (IOException e) {
			stop();
		}
	}
	
	public void receive(){
		try {
			while(!isShutDown){
				Socket client = server.accept() ;
				new Thread(new Dispatcher(client)).start(); 
			}
				
					
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			stop() ;
		}
	}
	public void stop(){
		isShutDown = true ;
		try {
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
