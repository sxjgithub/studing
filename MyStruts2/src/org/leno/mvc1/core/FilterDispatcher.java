package org.leno.mvc1.core;

import java.io.IOException;

import java.lang.reflect.Method;

import java.util.HashMap;

import java.util.Map;

import javax.servlet.Filter;

import javax.servlet.FilterChain;

import javax.servlet.FilterConfig;

import javax.servlet.ServletException;

import javax.servlet.ServletRequest;

import javax.servlet.ServletResponse;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

public class FilterDispatcher implements Filter {

	private Map<String, ActionConfig> actionMap = new HashMap<String, ActionConfig>();

	public void init(FilterConfig filterConfig) throws ServletException {

	// 得到框架核心配置文件mvc1.xml在web服务器上的物理路径，然后解析XML

		String webPath = getClass().getClassLoader().getResource("MyStruts2.xml")

				.getPath();

		try {
			ActionConfig ac = ConfigUtil.parseConfigFile(webPath);
			 actionMap.put(ac.getName(), ac);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("static-access")

	public void doFilter(ServletRequest req, ServletResponse res,

			FilterChain chain) throws IOException, ServletException {

		// 针对HTTP协议,将请求和响应对象还原为HTTP类型

		HttpServletRequest request = (HttpServletRequest) req;

		HttpServletResponse response = (HttpServletResponse) res;

		// 设置请求和响应的编码方式，避免乱码

		request.setCharacterEncoding("utf-8");

		response.setCharacterEncoding("utf-8");

		// 得到request的请求路径

		String uri = request.getServletPath();

 // 如果后缀不为.action,不需要该核心Filter控制，直接过关

		if (!uri.endsWith(".action")) {

	 chain.doFilter(request, response);//-------------------------------
//
	 return;

 }
 // 解析Request的路径

	int start = uri.indexOf("/");

	int end = uri.lastIndexOf(".");

	String path = uri.substring(start + 1, end);

	//由path找到相对应的ActionConfig类，它里面包含了所有Action的相关信息

	ActionConfig config = actionMap.get(path);

	if (config == null) {

		response.setStatus(response.SC_NOT_FOUND);

		return;

	}

	//由ActionConfig得到Action类的完整名字

	String clzActionName = config.getClzName();

	// 实例化Action对象, 不存在给出错误提示

	Object action = getAction(clzActionName);

	if (action == null) {
		
		response.setStatus(response.SC_NOT_FOUND);

		return;

	}

	// 前置拦截,取得request里面的参数，调用Action的set方法给属性设置值

	BeanUtil.requestToAction(request, action);

	// 执行Action的无参方法，例如execute()

	String result = executeAction(config, action);

	// 后置拦截,调用Action的get方法,将所有Action的属性放置到request区域

	BeanUtil.actionToRequest(request, action);

	// 返回相应的JSP页面

	if (result == null) {

		response.setStatus(response.SC_NOT_FOUND);

		return;

	}

	request.getRequestDispatcher(result).forward(request, response);
	}


	private String executeAction(ActionConfig config, Object action) {

		String method = config.getMethod();

		String result = null;

		try {

			Method callMethod = action.getClass().getMethod(method,

					new Class[] {});

			result = (String) callMethod.invoke(action, new Object[] {});

		} catch (Exception e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}
		return config.getResultMap().get(result);
		}




	private Object getAction(String clzActionName) {

		Object action = null;

		try {

			action = Class.forName(clzActionName).newInstance();

		} catch (Exception e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		return action;
		}


	public void destroy() {

		// TODO Auto-generated method stub



	}

	

}
