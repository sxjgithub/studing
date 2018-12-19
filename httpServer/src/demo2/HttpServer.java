package demo2;
/*���ܣ����տͻ�����Ϣ������ӡ������ͬʱ����һ��html����
 * �Ľ����ܽ��տ��к��������
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
			
	//��ȡ���ݣ�������Զ�ȡ���к�������ݣ���ǰһ��������ͬ--------------------------------------------------------
			byte[] data = new byte[10240] ;
			int len = client.getInputStream().read(data) ; //��ȡrequest����
			String msg = new String(data, 0, len).trim() ;
					System.out.println(msg);
					
	//response����
			StringBuilder responseContext = new StringBuilder() ;
			//��Ӧ����
			responseContext.append("<html><body><h3>hello Tomcat</h3><body></html>") ;
			
			StringBuilder response = new StringBuilder() ;
			//���HTTPЭ��汾��״̬�롢����
			response.append("HTTP/1.1").append(BLANK).
				append("200").append(BLANK).append("OK").append(CRLF) ;
			//�����Ӧͷ��Response Head��
			response.append("Server:sxj Server").append(CRLF) ;
			response.append("Content-type:text/html;charset= GBK").append(CRLF) ;//��Ӧ�ı����ǳ���Ҫ
			response.append("Content-Length:")//��Ӧ���ĳ��ȣ��ǳ���Ҫ
				.append(responseContext.toString().getBytes().length).append(CRLF) ;
			//���crlf
			response.append(CRLF) ;
			//�����Ӧ����
			response.append(responseContext) ;
			
	//����response
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
