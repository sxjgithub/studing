package demo7;

import java.util.HashMap;
import java.util.Map;


public class ServletContext {
	//Ϊÿһ��servletȡ����������loginģ��-->LoginServlet
	private Map<String, Servlet> servlet ;
	//url-->Login : ���ܶ��url ��Ӧһ��Login, �磺/log-->Login  ,/login-->Login
	private Map<String, String> mapping ;
	
	public ServletContext() {
		this.servlet =  new HashMap<String,	Servlet>() ;
		this.mapping = new HashMap<String, String>() ;
	}
	public Map<String, Servlet> getServlet() {
		return servlet;
	}
	public void setServlet(Map<String, Servlet> servlet) {
		this.servlet = servlet;
	}
	public Map<String, String> getMapping() {
		return mapping;
	}
	public void setMapping(Map<String, String> mapping) {
		this.mapping = mapping;
	}
	
	
}
