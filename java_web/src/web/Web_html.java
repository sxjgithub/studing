package web;
/*使用方法：运行此程序，然后打开浏览器输入：localhost:8887
 * */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.invoke.ConstantCallSite;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Pattern;
//为什么只有主动关闭了客户端，然后才在服务器端界面才出现发送过去的数据
import javax.xml.crypto.Data;

//建立tcp socket
public class Web_html {
	
	public static void main(String args[]) throws IOException{
		String HTML_ROOT_DIR = "./html";
		ServerSocket server_socket = new ServerSocket(8887);
		
			Socket client_socket = server_socket.accept();
			PrintStream out = new PrintStream(client_socket.getOutputStream());
			Scanner scan = new Scanner(client_socket.getInputStream());
			scan.useDelimiter(" ");
			
			
			//-------------------------------------注意是这里卡住了，why？
			//接收request_data
			String request_data = "" ;
//***************************************************************************************************
			boolean flag = true;
			while(flag){
				if(scan.hasNext()){  //注意，没有数据来scan.hasNext()会一直堵塞，等待数据来，-----------------------------------------------
					String temp = scan.next() ;
					if(temp.contains("Accept-Language")){  //当收到这个字符串 的时候，主动跳出scan.hasNext()
						flag = false ;
					}
					request_data += temp;
					 //System.out.println(scan.next().trim());
				}
					 
				
			}
			System.out.println("收到的request:\n" + request_data); //------------------------------------
//*****************************************************************************************************************			
			
			
			
			
			//解析request_data
			//String ddata = request_data.split("\n")[0];
			//System.out.println("********" + ddata); // "GET /test.html HTTP/1.1"----------------
			//String pa = Pattern.compile("").matcher(ddata).matches();--------------------
			//System.out.println(pa);------------------------------------
			
			//组织response_data
			String request_header = "HTTP/1.1 200 OK\r\n" + "Server: My server\r\n";
			
			File file = new File(HTML_ROOT_DIR + File.separator +"test.html"); //------------------
			Scanner scan_file = new Scanner(new FileInputStream(file));
			scan_file.useDelimiter("\n");
			String file_data = "";
			while(scan_file.hasNext()){
				file_data += scan_file.next().trim() + "\n";
			}
			scan_file.close();
			
			String request_body = file_data;
			//System.out.println("+++++++\n" + request_body);
			String request = request_header + "\r\n" + request_body ; 
			
			//发送response_data
		
			System.out.println("\n要回给客户端的response:\n" + request);  //能正常显示出来-------------------------------------------
			out.print(request);//-------------------------------为什么浏览器接收不到数据？，难
									//道是print方法不行？                    
			scan.close();
			out.close();
			client_socket.close();
			
			//break;
		server_socket.close();
	}
	
}
