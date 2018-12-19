package demo1;
/*功能：接收客户端信息，并打印出来
 * 缺陷：不能接收空行后面的数据
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class HttpServer {
	private ServerSocket server ;
	
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
			String msg = null ; //接收客户端的请求信息
			BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream())) ;
			while((msg = br.readLine()).length() > 0){ //读取request
				if(null == msg) { //如果没有收到的信息是空，则表示接收结束
					break ;
				}//注意：如果如果传过来的数据包含一行空字符，则这种方法就读取不到空行后面的数据
				sb.append(msg) ;
				sb.append("\r\n") ;
				
				
				
			}
			String requestInfo = sb.toString().trim();
			System.out.println(requestInfo) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
