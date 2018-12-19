package demo5;
/*此版本封装了Servlet类
功能：接收request数据（并分析其中的参数，method等），根据request 返回相应的response
 * */
import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;


public class HttpServer {
	private ServerSocket server ;
	public static final String CRLF = "\r\n" ;
	public static final String BLANK = " " ;
	
	public static void main(String[] args) {
		HttpServer httpServer = new HttpServer() ;
		httpServer.start();
	}
	
	public void start(){
		try {
			 server = new ServerSocket(8888) ;
			 this.receive();
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void receive(){
		try {
			Socket client = server.accept() ;
			
	//读取数据：--------------------------------------------------------
			Request request = new Request(client.getInputStream()) ;
					
	//response		
			Response response = new Response(client.getOutputStream()) ;
			Serlvet ser = new Serlvet() ;
			ser.service(request, response);
		
			response.pushToClient(200);
					
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
