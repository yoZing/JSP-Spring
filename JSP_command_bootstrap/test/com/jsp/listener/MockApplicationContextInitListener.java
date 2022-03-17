package com.jsp.listener;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.jsp.context.ApplicationContext;

public class MockApplicationContextInitListener {

    public void contextInitialized(String beanConfigXml)  { 
    	
//    	System.out.println("Listener start!!!"); 
//    	ServletContext ctx = ctxEvent.getServletContext();
//    	
//    	String beanConfigXml = ctx.getInitParameter("contextConfigLocation");    	
    	
    /*	
    	리스너가 ServletContext application 이 생성되는 것을 지켜보다 생성이 감지 되면
    	해당 객체를 가지고 있는 Event 에게 applicaion 객체를 받아낸가.
    	
    	
    	
    */	
    	
    	if (beanConfigXml == null)
    		return;
    	
    	beanConfigXml = beanConfigXml.replace("classpath:", "res/").replace("/", File.separator);
//    	System.out.println(beanConfigXml);
    	
    	try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = factory.newDocumentBuilder();
			Document document = documentBuilder.parse(beanConfigXml);

			Element root = document.getDocumentElement();
			
//			System.out.println(root.getTagName());
			
			if (root == null || !root.getTagName().equals("beans")) return;
			
			Map<String, Object> applicationContext = ApplicationContext.getApplicationContext();
			
			NodeList beans = root.getElementsByTagName("bean");
			
			for (int i = 0; i < beans.getLength(); i++) {
				Node bean = beans.item(i);
				if (bean.getNodeType() == Node.ELEMENT_NODE) {
					Element ele = (Element) bean;
					String id = ele.getAttribute("id");
					String classType = ele.getAttribute("class");
//					System.out.printf("id : %s, class = %s\n", id, classType);
					
					// map instance put
					Class<?> cls = Class.forName(classType);
					Object targetObj = cls.newInstance(); // single tone
					applicationContext.put(id, targetObj);
					
//					System.out.println("id : " + id + ", class : " + targetObj);
				}
			}
			
			// 의존 주입
			for (int i = 0; i < beans.getLength(); i++) {
				Node bean = beans.item(i);
				if (bean.getNodeType() == Node.ELEMENT_NODE) {
					Element eleBean = (Element) bean;
					
					NodeList properties = bean.getChildNodes();
					for (int j = 0; j < properties.getLength(); j++) {
						Node property = properties.item(j);
						if (!property.getNodeName().equals("property")) continue;
						
						if (property.getNodeType() == Node.ELEMENT_NODE) {
							Element ele = (Element) property;
							String name = ele.getAttribute("name");
							String ref = ele.getAttribute("ref-value");
							
//							System.out.printf("name = %s,  ref-value = %s\n", name, ref);
							
							String setMethodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
							
							String className = eleBean.getAttribute("class");
							Class<?> classType = Class.forName(className);
							
							Method[] methods = classType.getMethods();
							if (methods != null) for (Method method : methods) {
								if (method.getName().contentEquals(setMethodName)) {
									method.invoke(applicationContext.get(eleBean.getAttribute("id")), applicationContext.get(ref));
									
									System.out.println("[invoke]" + applicationContext.get(eleBean.getAttribute("id")) + ":" + applicationContext.get(ref));
									
								}
							}
							
						}
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
}
