package server;

import java.util.Map;

import servlet.Servlet;

public class WebApp {
	private static ServletContext contxt ;
	
	static{
		contxt = new ServletContext() ;
		
		/*Map<String, String> mapping = contxt .getMapping() ;
		mapping.put("/login", "login") ;
		mapping.put("/log", "login") ;
		
		Map<String, String> servlet = contxt.getServlet() ;
		
		servlet.put("login", "demo.LoginServlet") ;*/
		try {
			contxt =WebParse.getServletContext("WEB_INFO/web.xml");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Servlet getServlet(String url){
		if((null == url) || (url =  url.trim()).equals("")){
			return null ;
		}
		
		//return contxt.getServlet().get(contxt.getMapping().get(url)) ;
		try {
			Class<?> c = Class.forName(contxt.getServlet().get(contxt.getMapping().get(url)));
			return (Servlet)c.newInstance();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
