package demo3;
/*此版本封装了Response类
 * 功能：接收客户端信息，并打印出来，同时返回一个html数据
 * */

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
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
			StringBuilder sb = new StringBuilder() ;
			
	//读取数据：这里可以读取空行后面的数据，与前一个方法不同--------------------------------------------------------
			byte[] data = new byte[10240] ;
			int len = client.getInputStream().read(data) ; //读取request数据
			String msg = new String(data, 0, len).trim() ;
					System.out.println(msg);
					
	//response		
			Response response = new Response(client.getOutputStream()) ;
			response.println("<html><body><h3>hello sxj11</h3><body></html>") ;
			response.pushToClient(200);
					
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
