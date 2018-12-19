package server;

import java.io.IOException;
import java.net.Socket;

import servlet.Servlet;

public class Dispatcher implements Runnable{
	private Response resp;
	private Request req ;
	private Socket client ;
	private int code = 200 ;
	
	public Dispatcher(Socket client) {
		this.client = client ;
	
		try {
			this.req = new Request(client.getInputStream()) ;
			this.resp = new Response(client.getOutputStream()) ;
			
		} catch (IOException e) {
			code = 500 ;
			return ;
		}
	}
	@Override
	public void run() {
		try {
			Servlet ser = WebApp.getServlet(req.getUrl()) ;
			if(null == ser){
				this.code =404 ;
			}else{
				ser.service(req, resp);
			}
			resp.pushToClient(code);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
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
