package demo7;
/*增加servletContext 映射：比如：login模块-->LoginServlet  
 * 						以及 url ---> login模块
 * */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class HttpServer1 {
	private ServerSocket server ;
	public static final String CRLF = "\r\n" ;
	public static final String BLANK = " " ;
	private boolean isShutDown = false ;
	
	public static void main(String[] args) {
		HttpServer1 httpServer = new HttpServer1() ;
		httpServer.start();
	}
	
	public void start(){
		start(8889);
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
				new Thread(new Dispatcher(server.accept())).start(); 
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
