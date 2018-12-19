package demo7;
/*功能：添加用户功能，添加映射url-->功能模块名称，添加映射功能模块名称--->对应的Servlet
 * 两映射集通过 功能模块名称联系， 
 * */
import java.util.Map;

public class WebApp {
	private  static ServletContext contxt;
	
	static{
		contxt = new ServletContext() ;
		
		Map<String, String> mapping = contxt.getMapping() ;
		mapping.put("/login", "login") ;
		mapping.put("/log", "login");
		//mapping.put("/reg", "register");
		
		Map<String, Servlet> servlet = contxt.getServlet() ;
		servlet.put("login", new LoginServlet()) ;
	}
	
//获取url对应的 Servlet
	public static Servlet getServlet(String url){
		if((null == url) || (url = url.trim()).equals("")){
			return null ;
		}
		return contxt.getServlet().get(contxt.getMapping().get(url)) ;
	}
}
