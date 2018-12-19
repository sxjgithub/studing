package demo8;

import java.io.IOException;
import java.net.Socket;

import sun.awt.RepaintArea;

public class Dispatcher implements Runnable{
	private Response resp;
	private Request req ;
	private Socket client ;
	private int code = 200 ;
	
	public Dispatcher(Socket client) {
		this.client = client ;
	
		try {
			this.resp = new Response(client.getOutputStream()) ;
			//����һ������ᱨ��-------------------------------------------------------
			this.req = new Request(client.getInputStream()) ;
		} catch (IOException e) {
			code = 500 ;
			return ;
		}
	}
	@Override
	public void run() {
		try {
			Servlet serv = WebApp.getServlet(req.getUrl()) ;
			if(null == serv){
				this.code = 404 ; //�Ҳ����Ĵ���
			}else{
				serv.service(req, resp);
			}
			resp.pushToClient(code);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			this.code = 500 ;
		}
		
		/*try {
			resp.pushToClient(500);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		try {
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
