package web;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class tcp_server {
	public static void main(String[] args) throws Exception{
		ServerSocket server_socket = new ServerSocket(8006);
		Socket client_socket = server_socket.accept();
		Scanner scan = new Scanner(client_socket.getInputStream());
		scan.useDelimiter("\n");
		PrintStream out = new PrintStream(client_socket.getOutputStream());
		boolean flag = true;
		String str = "HTTP/1.1 200 OK\r\nServer: My server\r\n\r\n<html><body><h2>你好阿</h2></body></html>" ;
		
		String var = "" ;
		while(flag){
			if(scan.hasNext()){
				String con = scan.next().trim() ;
				if("byebye".equalsIgnoreCase(con)){
					flag = false ;
				}else{
					var = var + con;
					System.out.println("客户端发来了：" + var) ;
				}
			}	
		}
		//System.out.println("客户端发来了：" + var) ;
		out.println(str);
		scan.close();
		out.close();
		client_socket.close();
		server_socket.close();
	}
}


