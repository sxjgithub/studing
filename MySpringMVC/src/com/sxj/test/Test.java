package com.sxj.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.New;
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

public class Test {
	public static void main(String[] args) throws Exception {
		DispacherServlet ds = new DispacherServlet();
		ds.doScanpackage("com.sxj");
	}
}

class DispacherServlet{
	List<String> classNames = new ArrayList<String>();
	Map<String, Object> beans = new HashMap<String, Object>();
	Map<String, Object> handlerMap = new HashMap<String, Object>();
	public void doScanpackage(String basePackage) throws Exception {
		//ɨ�����õ���·���µ�������  C:\..\Workspaces\..\MySpringMVC\src\ + service.impl
		String urlString = this.getClass().getClassLoader().getResource("" + 
				basePackage.replaceAll("\\.", "/")).getPath(); //  
		String newUrlString = urlString.substring(1,urlString.length()).replace("%20", " ");
		System.out.println(newUrlString); //--------------------------------------
		URL url = null;
		try {
			url = new URL(newUrlString);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String fileStr = url.getFile(); //C:/../Workspaces/../MySpringMVC/src/sxj 
		
		File file = new File(fileStr);
		String[] filesName = file.list(); //�г�·���µ������ļ��������ļ��У�
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
	
	//��tomcat�����������ڳ�ʼ����been map
	public void init(ServletConfig config){
		
		//���еĴ���ʼ�����������classɨ������� ���磺service.SxjServiceImpl.class
		//��ͬ @baseScans
		
		//ɨ��class�ļ�
		//doScanpackage("");
		
		//1.����ҵ��bean
		doInstance();
		
		//��ServiceImpl��ע�뵽��������
		doAutoWired();
		
		//URLӳ��---> method ---> /sxj1/query --->method
		
		handlerMapping();
		
	}

	private void handlerMapping() {
		for(Map.Entry<String, Object> entry:beans.entrySet()){
			Object instance = entry.getValue();
			Class<?> clazz = instance.getClass();
			if(clazz.isAnnotationPresent(EnjoyController.class)){
				EnjoyRequestMapping mapping = clazz.getAnnotation(EnjoyRequestMapping.class);
				
				String classPath = mapping.value(); // /sxj1
				
				Method[] methods = clazz.getMethods(); //��ȡ����������з���
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
				Field[] fields = clazz.getDeclaredFields(); //�õ������涨�����������
				for(Field field: fields){
					if(clazz.isAnnotationPresent(EnjoyAutoWired.class)){
						EnjoyAutoWired auto = field.getAnnotation(EnjoyAutoWired.class);
						String key = auto.value();
						
						Object obj = beans.get(key); //service�����õ���
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
					//������
					Object instance = clazz.newInstance(); //map.put(key, instance);
					EnjoyRequestMapping mapping = clazz.getAnnotation(EnjoyRequestMapping.class);
					String key = mapping.value(); //  /sxj -->key
					beans.put(key, instance);
		 		}else if(clazz.isAnnotationPresent(EnjoyService.class)){ 
		 			//ServiceImpl ��
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
 
	
	

	
	
	
	
}

