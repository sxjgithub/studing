package web;
/*ʹ�÷��������д˳���Ȼ�����������룺localhost:8887
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
//Ϊʲôֻ�������ر��˿ͻ��ˣ�Ȼ����ڷ������˽���ų��ַ��͹�ȥ������
import javax.xml.crypto.Data;

//����tcp socket
public class Web_html {
	
	public static void main(String args[]) throws IOException{
		String HTML_ROOT_DIR = "./html";
		ServerSocket server_socket = new ServerSocket(8887);
		
			Socket client_socket = server_socket.accept();
			PrintStream out = new PrintStream(client_socket.getOutputStream());
			Scanner scan = new Scanner(client_socket.getInputStream());
			scan.useDelimiter(" ");
			
			
			//-------------------------------------ע�������￨ס�ˣ�why��
			//����request_data
			String request_data = "" ;
//***************************************************************************************************
			boolean flag = true;
			while(flag){
				if(scan.hasNext()){  //ע�⣬û��������scan.hasNext()��һֱ�������ȴ���������-----------------------------------------------
					String temp = scan.next() ;
					if(temp.contains("Accept-Language")){  //���յ�����ַ��� ��ʱ����������scan.hasNext()
						flag = false ;
					}
					request_data += temp;
					 //System.out.println(scan.next().trim());
				}
					 
				
			}
			System.out.println("�յ���request:\n" + request_data); //------------------------------------
//*****************************************************************************************************************			
			
			
			
			
			//����request_data
			//String ddata = request_data.split("\n")[0];
			//System.out.println("********" + ddata); // "GET /test.html HTTP/1.1"----------------
			//String pa = Pattern.compile("").matcher(ddata).matches();--------------------
			//System.out.println(pa);------------------------------------
			
			//��֯response_data
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
			
			//����response_data
		
			System.out.println("\nҪ�ظ��ͻ��˵�response:\n" + request);  //��������ʾ����-------------------------------------------
			out.print(request);//-------------------------------Ϊʲô��������ղ������ݣ�����
									//����print�������У�                    
			scan.close();
			out.close();
			client_socket.close();
			
			//break;
		server_socket.close();
	}
	
}
