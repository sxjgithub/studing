package demo8;

import java.util.HashMap;
import java.util.Map;


public class ServletContext {
	//为每一个servlet取个别名，如Login模块-->demo8.LoginServlet
	private Map<String, String> servlet ;
	//url-->Login : 可能多个url 对应一个Login, 如：/log-->Login模块  ,/login-->Login模块
	private Map<String, String> mapping ;
	
	public ServletContext() {
		this.servlet =  new HashMap<>() ;
		this.mapping = new HashMap<>() ;
	}
	public Map<String, String> getServlet() {
		return servlet;
	}
	public void setServlet(Map<String, String> servlet) {
		this.servlet = servlet;
	}
	public Map<String, String> getMapping() {
		return mapping;
	}
	public void setMapping(Map<String, String> mapping) {
		this.mapping = mapping;
	}
	
	
}
