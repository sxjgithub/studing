package demo2;
/*功能：接收客户端信息，并打印出来，同时返回一个html数据
 * 改进：能接收空行后面的数据
 * */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
					
	//response数据
			StringBuilder responseContext = new StringBuilder() ;
			//响应正文
			responseContext.append("<html><body><h3>hello Tomcat</h3><body></html>") ;
			
			StringBuilder response = new StringBuilder() ;
			//添加HTTP协议版本、状态码、描述
			response.append("HTTP/1.1").append(BLANK).
				append("200").append(BLANK).append("OK").append(CRLF) ;
			//添加响应头（Response Head）
			response.append("Server:sxj Server").append(CRLF) ;
			response.append("Content-type:text/html;charset= GBK").append(CRLF) ;//响应文本：非常重要
			response.append("Content-Length:")//响应正文长度：非常重要
				.append(responseContext.toString().getBytes().length).append(CRLF) ;
			//添加crlf
			response.append(CRLF) ;
			//添加响应正文
			response.append(responseContext) ;
			
	//发送response
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream())) ;
			bw.write(response.toString());
			bw.flush();
			bw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
