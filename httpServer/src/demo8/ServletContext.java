package demo8;

import java.util.HashMap;
import java.util.Map;


public class ServletContext {
	//Ϊÿһ��servletȡ����������Loginģ��-->demo8.LoginServlet
	private Map<String, String> servlet ;
	//url-->Login : ���ܶ��url ��Ӧһ��Login, �磺/log-->Loginģ��  ,/login-->Loginģ��
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
