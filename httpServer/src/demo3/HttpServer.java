package demo3;
/*�˰汾��װ��Response��
 * ���ܣ����տͻ�����Ϣ������ӡ������ͬʱ����һ��html����
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
			
	//��ȡ���ݣ�������Զ�ȡ���к�������ݣ���ǰһ��������ͬ--------------------------------------------------------
			byte[] data = new byte[10240] ;
			int len = client.getInputStream().read(data) ; //��ȡrequest����
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
