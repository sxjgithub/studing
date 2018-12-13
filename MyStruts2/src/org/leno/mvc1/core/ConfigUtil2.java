package org.leno.mvc1.core;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

import java.util.List;

import java.util.Map;

import javax.enterprise.inject.New;
import javax.ws.rs.ForbiddenException;

import org.dom4j.Document;

import org.dom4j.DocumentException;

import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.OutputStreamWriter;  
import java.util.Iterator;  
import java.util.List;

import org.apache.taglibs.standard.tag.common.xml.IfTag;
import org.dom4j.Attribute;  
import org.dom4j.Document;  
import org.dom4j.DocumentHelper;
import org.dom4j.Element;  
import org.dom4j.io.OutputFormat;  
import org.dom4j.io.SAXReader;  
import org.dom4j.io.XMLWriter;  
//import org.junit.Test; 

public class ConfigUtil2 {
	private ActionConfig actionConfig = new ActionConfig();
	public static void main(String[] args) throws Exception {
		//parseConfigFile("C:/Users/Administrator.USER-20180425YG/.myeclipse/MyStruts2/src/MyStruts2.xml", null);
		ConfigUtil2 c = new ConfigUtil2();
		c.readXMLDemo();
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

	 /** 
     * 遍历当前节点元素下面的所有(元素的)子节点 
     *  
     * @param node 
     */  
    public void ParseNodes(Element node) {
    	Map<String, String> resultMap = new HashMap<String, String>(); 
    	if("action".equalsIgnoreCase(node.getName()){
    		//System.out.println("当前节点的名称：：" + node.getName());
    		// 获取当前节点的所有属性节点  
    		List<Attribute> list = node.attributes();
            for (Attribute attr : list) {  
                System.out.println(attr.getText() + "-----" + attr.getName()  
                        + "---" + attr.getValue()); 
                if("name".equalsIgnoreCase(attr.getName())){
                	actionConfig.setName(attr.getValue());
                }else if("method".equalsIgnoreCase(attr.getName())){
                	actionConfig.setMethod(attr.getValue());
                }else if("class".equalsIgnoreCase(attr.getName())){
                	actionConfig.setClzName(attr.getValue());
				}
            }  
         }else if("result".equalsIgnoreCase(node.getName()){
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
		
    		
    		
    		
    		
    		
    		
    		
    		
        /*System.out.println("当前节点的名称：：" + node.getName());  
        // 获取当前节点的所有属性节点  
        List<Attribute> list = node.attributes();  
        // 遍历属性节点  
        for (Attribute attr : list) {  
            System.out.println(attr.getText() + "-----" + attr.getName()  
                    + "---" + attr.getValue()); 
        }  

        if (!(node.getTextTrim().equals(""))) {  
            System.out.println("文本内容：：：：" + node.getText());  
        }  

        // 当前节点下面子节点迭代器  
        Iterator<Element> it = node.elementIterator();  
        // 遍历  
        while (it.hasNext()) {  
            // 获取某个子节点对象  
            Element e = it.next();  
            // 对子节点进行遍历  
            listNodes(e);  
        }  
    }  */

    /** 
     * 介绍Element中的element方法和elements方法的使用 
     *  
     * @param node 
     */  
    /*public void elementMethod(Element node) {  
        // 获取node节点中，子节点的元素名称为supercars的元素节点。  
        Element e = node.element("supercars");  
        // 获取supercars元素节点中，子节点为carname的元素节点(可以看到只能获取第一个carname元素节点)  
        Element carname = e.element("carname");  

        System.out.println(e.getName() + "----" + carname.getText());  

        // 获取supercars这个元素节点 中，所有子节点名称为carname元素的节点 。  

        List<Element> carnames = e.elements("carname");  
        for (Element cname : carnames) {  
            System.out.println(cname.getText());  
        }  

        // 获取supercars这个元素节点 所有元素的子节点。  
        List<Element> elements = e.elements();  

        for (Element el : elements) {  
            System.out.println(el.getText());  
        }  

    }  */



	@SuppressWarnings("unchecked")

	public static void parseConfigFile(String fileName, Map<String, ActionConfig> map) {
		// 创建saxReader对象  
        SAXReader reader = new SAXReader();  
        // 通过read方法读取一个文件 转换成Document对象  
        Document document = reader.read(new File(fileName));  
        //获取根节点元素对象  
        Element node = document.getRootElement();  
        //遍历所有的元素节点  
        //listNodes(node); 

        //elementMethod(node);
        
        
		

			
		}
	
}

