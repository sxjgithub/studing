package com.sxj.servlet;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxj.annotatioin.EnjoyAutoWired;
import com.sxj.annotatioin.EnjoyController;
import com.sxj.annotatioin.EnjoyRequestMapping;
import com.sxj.annotatioin.EnjoyRequestParam;
import com.sxj.annotatioin.EnjoyService;
import com.sxj.controller.SxjController;

public class DispacherServlet extends HttpServlet{
	
	List<String> classNames = new ArrayList<String>();
	Map<String, Object> beans = new HashMap<String, Object>();
	Map<String, Object> handlerMap = new HashMap<String, Object>();
	
	
	//在tomcat启动过程中在初始化，been map
	public void init(ServletConfig config){
		
		//所有的待初始化创建对象的class扫描出来。 比如：service.SxjServiceImpl.class
		//如同 @baseScans
		
		//扫描class文件
		doScanpackage("com.sxj");
		
		//1.创建业务bean
		doInstance();
		
		//将ServiceImpl类注入到控制类中
		doAutoWired();
		
		//URL映射---> method ---> /sxj1/query --->method
		
		handlerMapping();
		
	}

	private void handlerMapping() {
		for(Map.Entry<String, Object> entry:beans.entrySet()){
			Object instance = entry.getValue();
			Class<?> clazz = instance.getClass();
			if(clazz.isAnnotationPresent(EnjoyController.class)){
				EnjoyRequestMapping mapping = clazz.getAnnotation(EnjoyRequestMapping.class);
				
				String classPath = mapping.value(); // /sxj1
				
				Method[] methods = clazz.getMethods(); //获取类里面的所有方法
				for(Method method: methods){
					if(method.isAnnotationPresent(EnjoyRequestMapping.class)){
						EnjoyRequestMapping methodMapping = method.getAnnotation(EnjoyRequestMapping.class);
						String methodPath = methodMapping.value(); //  /query
						String path = classPath + methodPath; //  /james/query-->method
						handlerMap.put(path, method);
					}else{
						continue;
					}
				}
			}
			
		}
		
	}

	private void doAutoWired() {

		for(Map.Entry<String, Object> entry:beans.entrySet()){
			Object instance = entry.getValue();
			Class<?> clazz = instance.getClass();
			
			if(clazz.isAnnotationPresent(EnjoyController.class)){
				Field[] fields = clazz.getDeclaredFields(); //拿到类里面定义的所有属性
				for(Field field: fields){
					if(clazz.isAnnotationPresent(EnjoyAutoWired.class)){
						EnjoyAutoWired auto = field.getAnnotation(EnjoyAutoWired.class);
						String key = auto.value();
						
						Object obj = beans.get(key); //service对象拿到了
						field.setAccessible(true);
						
						try{
							field.set(instance, obj);
						}catch(Exception e){
							e.printStackTrace(); 
						}
					}else{
						continue;
					}
				}
			}else{
				continue;
			}
		}
		
	}

	private void doInstance() {
		for(String className: classNames){
			String cn = className.replace(".class", ""); //controller.SxjController.class
			
			try {
				Class<?> clazz = Class.forName(cn);
				
				if (clazz.isAnnotationPresent(EnjoyController.class)){
					//控制类
					Object instance = clazz.newInstance(); //map.put(key, instance);
					EnjoyRequestMapping mapping = clazz.getAnnotation(EnjoyRequestMapping.class);
					String key = mapping.value(); //  /sxj -->key
					beans.put(key, instance);
		 		}else if(clazz.isAnnotationPresent(EnjoyService.class)){ 
		 			//ServiceImpl 类
		 			Object instance = clazz.newInstance(); //map.put(key, instance);
		 			EnjoyService service = clazz.getAnnotation(EnjoyService.class);
					String key = service.value(); //  sxj -->key
					beans.put(key, instance);
		 		}else{
		 			continue;
		 		}
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
 
	
	private void doScanpackage(String basePackage) {
		//扫描编译好的类路径下的所有类  C:\..\Workspaces\..\MySpringMVC\src\ + com.sxj
		String urlString = this.getClass().getClassLoader().getResource("" 
				+ basePackage.replaceAll("\\.", "/")).getPath();
		String newUrlString = urlString.substring(1,urlString.length()).replace("%20", " ");
		URL url = null;
		try {
			url = new URL(newUrlString);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String fileStr = url.getFile(); //C:/../Workspaces/../MySpringMVC/src/sxj 
		
		File file = new File(fileStr);
		String[] filesName = file.list(); //列出路径下的所有文件（包括文件夹）
		for(String fileName : filesName){
			System.out.println(fileName + "------------------------------------------------------");
		}
		
		for(String path: filesName){
			File filePath = new File(fileStr + path); //service.impl.SxjServiceImpl
			
			if(filePath.isDirectory()){
				doScanpackage(basePackage + "." + path);
			}else{
				classNames.add(basePackage + "." + filePath.getName());//service .SxjServiceImpl.class
			}
		}
		
		
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		this.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI(); //  /MySpringMVC/sxj1/query
		String context = req.getContextPath(); // /MySpringMVC
		String path = uri.replace(context, "");  // path =  /sxj1/query
		
		Method method = (Method) handlerMap.get(path);
		
		SxjController instance = (SxjController) beans.get("/" + path.split("/")[1]); //  /sxj
		Object[] args = hand(req, resp, method);
		try {
			method.invoke(instance, args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  //从底层调用方法， args[]方法里的参数数组
		
		
		this.doPost(req, resp);
	}
	/**
	 * 考虑用策略模式。。。。
	 * @param request
	 * @param response
	 * @param method
	 * @return
	 */
	private static Object[] hand(HttpServletRequest request, HttpServletResponse response, Method method){
		//拿到当前待执行的方法有哪些参数
		Class<?>[] paramClazzs = method.getParameterTypes();
		//根据参数的个数，new一个参数的数组，将方法里的所有参数赋值到args中
		Object[] args = new Object[paramClazzs.length];
		
		int args_i = 0;
		int index = 0;
		for(Class<?> paramClazz: paramClazzs){
			if(ServletRequest.class.isAssignableFrom(paramClazz)){
				args[args_i++] = request;
			}
			if(ServletResponse.class.isAssignableFrom(paramClazz)){
				args[args_i++] = response;
			}
			//从0-3判断有没有Requestparam注解，很明显paramClazz为0和1时，不是，
			//当为2和3时为@RequestParam,需要解析
			//[@annotation.EnjoyRequestparam(valuename)]
			Annotation[] paramAns = method.getParameterAnnotations()[index];
			if(paramAns.length > 0){
				for(Annotation paramAn: paramAns){
					if(EnjoyRequestParam.class.isAssignableFrom(paramAn.getClass())){
						EnjoyRequestParam rp = (EnjoyRequestParam) paramAn;
						args[args_i++] = request.getParameter(rp.value());
					}
				}
			}
			index++;
		}
		return args;
	}
	
	
	
}
