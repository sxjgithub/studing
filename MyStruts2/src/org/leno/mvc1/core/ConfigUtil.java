package org.leno.mvc1.core;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

import java.util.List;

import java.util.Map;

import javax.enterprise.inject.New;

import org.dom4j.Document;

import org.dom4j.DocumentException;

import org.dom4j.Element;

import org.dom4j.io.SAXReader;

import java.io.File;  
import java.io.FileOutputStream;  
import java.io.OutputStreamWriter;  
import java.util.Iterator;  
import java.util.List;  
import org.dom4j.Attribute;  
import org.dom4j.Document;  
import org.dom4j.DocumentHelper;
import org.dom4j.Element;  
import org.dom4j.io.OutputFormat;  
import org.dom4j.io.SAXReader;  
import org.dom4j.io.XMLWriter; 

  
//import org.junit.Test; 

public class ConfigUtil {
	private static Map<String, String> resultMap = new HashMap<String, String>();
	private static ActionConfig actionConfig = new ActionConfig();
	private static Map<String, ActionConfig> map = new HashMap<String, ActionConfig>();
	
	public static void main(String[] args) throws Exception {
		//parseConfigFile("C:/Users/Administrator.USER-20180425YG/.myeclipse/MyStruts2/src/MyStruts2.xml", null);
		ConfigUtil c = new ConfigUtil();
		ActionConfig ac = c.parseConfigFile("C:/Users/Administrator.USER-20180425YG/.myeclipse/MyStruts2/src/MyStruts2.xml");
		
	}
	
	public void readXMLDemo() throws Exception {  

        // 创建saxReader对象  
        SAXReader reader = new SAXReader();  
        // 通过read方法读取一个文件 转换成Document对象  
        Document document = reader.read(new File("C:/Users/Administrator.USER-20180425YG/.myeclipse/MyStruts2/src/MyStruts2.xml"));  
        //获取根节点元素对象  
        Element node = document.getRootElement();  
        //遍历所有的元素节点  
        //listNodes(node); 

        //elementMethod(node);

    }

	@SuppressWarnings("unchecked")

	public  static ActionConfig parseConfigFile(String fileName) throws DocumentException {
		// 创建saxReader对象  
        SAXReader reader = new SAXReader();  
        // 通过read方法读取一个文件 转换成Document对象  
        Document document = reader.read(new File(fileName));  
        //获取根节点元素对象  
        Element node = document.getRootElement();  
        //遍历所有的元素节点  
        //listNodes(node); 

        //elementMethod(node);
        ParseNodes(node);
        actionConfig.setResultMap(resultMap);
       return actionConfig;
		}
	
	public  static void ParseNodes(Element node) {
		if("action".equalsIgnoreCase(node.getName())){
			//System.out.println("当前节点的名称：：" + node.getName());
			// 获取当前节点的所有属性节点
			List<Attribute> list = node.attributes();
			for (Attribute attr : list) {
				System.out.println(attr.getText() + "-----" + attr.getName() + "---" + attr.getValue());
				if("name".equalsIgnoreCase(attr.getName())){
				
					actionConfig.setName(attr.getValue());	
					map.put(attr.getValue(), null);
				}else if("method".equalsIgnoreCase(attr.getName())){
					actionConfig.setMethod(attr.getValue());
				}else if("class".equalsIgnoreCase(attr.getName())){
					actionConfig.setClzName(attr.getValue());
				}
			}
		}else if("result".equalsIgnoreCase(node.getName())){
			List<Attribute> list = node.attributes();
			for(Attribute attr : list){
				if("name".equalsIgnoreCase(attr.getName())){
					String url = node.getTextTrim();
					resultMap.put(attr.getValue(), url);
				}
			}
		}
	      // 当前节点下面子节点迭代器  
	         Iterator<Element> it = node.elementIterator();  
	         // 遍历  
	         while (it.hasNext()) {  
	             // 获取某个子节点对象  
	             Element e = it.next();  
	             // 对子节点进行遍历  
	             ParseNodes(e);  
	         }
	  }
	
}

