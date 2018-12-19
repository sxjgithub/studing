package demo1;
/*���ܣ����տͻ�����Ϣ������ӡ����
 * ȱ�ݣ����ܽ��տ��к��������
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
			String msg = null ; //���տͻ��˵�������Ϣ
			BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream())) ;
			while((msg = br.readLine()).length() > 0){ //��ȡrequest
				if(null == msg) { //���û���յ�����Ϣ�ǿգ����ʾ���ս���
					break ;
				}//ע�⣺�����������������ݰ���һ�п��ַ��������ַ����Ͷ�ȡ�������к��������
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
