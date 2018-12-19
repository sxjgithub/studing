package demo4;
/*�˰汾��װ��Request��
���ܣ�����request���ݣ����������еĲ�����method�ȣ�������request ������Ӧ��response
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
			
	//��ȡ���ݣ�--------------------------------------------------------
			Request request = new Request(client.getInputStream()) ;
					
	//response		
			Response response = new Response(client.getOutputStream()) ;
			response.println("<html><body><h3>hel") ;
			response.println(request.getParamter("uname"));
			response.println("</h3></body></html>") ;
		
			response.pushToClient(200);
					
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
