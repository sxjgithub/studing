package demo8;

import java.util.Map;

public class WebApp {
	private  static ServletContext contxt;
	
	static{
		contxt = new ServletContext() ;
		
		Map<String, String> mapping = contxt.getMapping() ;
		mapping.put("/login", "login") ;
		mapping.put("/log", "login");
		//mapping.put("/reg", "register");
		
		Map<String, String> servlet = contxt.getServlet() ;
		servlet.put("login", "demo8.LoginServlet") ;
	}
	
	public static Servlet getServlet(String url) throws Exception{
		if((null == url) || (url = url.trim()).equals("")){
			return null ;
		}
		//根据字符串（完整路径）创建对象
		//return contxt.getServlet().get(contxt.getMapping().get(url)) ;
		String name = contxt.getServlet().get(contxt.getMapping().get(url)) ;
		return (Servlet)Class.forName("demo8.LoginServlet").newInstance() ;//确保空构造的存在
	}
}
