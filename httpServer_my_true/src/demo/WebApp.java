package demo;

import java.util.Map;

public class WebApp {
	private static ServletContext contxt ;
	
	static{
		contxt = new ServletContext() ;
		
		Map<String, String> mapping = contxt .getMapping() ;
		mapping.put("/login", "login") ;
		mapping.put("/log", "login") ;
		
		Map<String, Servlet> servlet = contxt.getServlet() ;
		
		servlet.put("login", new LoginServlet()) ;
		
	}
	
	public static Servlet getServlet(String url){
		if((null == url) || (url =  url.trim()).equals("")){
			return null ;
		}
		return contxt.getServlet().get(contxt.getMapping().get(url)) ;
	}
}
