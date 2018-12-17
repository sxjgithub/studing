package demo4_AIO;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
//为什么该客户端不能收到数据？ 
public class tcp_client1 {
	public static void main(String args[]) throws Exception{
		Socket client_socket = new Socket("localhost",  12345);
		PrintStream out = new PrintStream(client_socket.getOutputStream());
		out.println("haha");
		out.println("byebye");
		String data = "" ;
		
		Scanner scan = new Scanner(client_socket.getInputStream());
		scan.useDelimiter("\n");
		while(scan.hasNext()){
			
			data += scan.next().trim();
			
		}
		System.out.println("client收到的response:" + data);  //---------------------------------
		scan.close();
		out.close();
		client_socket.close();
	}
}
