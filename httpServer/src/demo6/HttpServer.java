package demo6;
/*�˰汾������̣߳���ÿһ���ͻ��˷���һ���߳���
���ܣ�����request���ݣ����������еĲ�����method�ȣ�������request ������Ӧ��response
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
