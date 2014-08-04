package com.zebra.common;


import org.springframework.beans.BeansException;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.zebra.business.craw.domain.CrawConfigBO;

/**
 * Service Layer 의 클래스 생성을 담당하는 Singleton 클래스.
 * 
 * @author 김형도
 * @version 0.1
 */
public class SpringBeanFactory implements ApplicationContextAware{

	private static ApplicationContext applicationContext ;
	
    /**
     * 서비스 클래스의 instance 를 생성한다.
     *
     * @param serivceClass 서비스 클래스의 Class.
     * @return
     * @throws Exception 
     */
    public static <T>T getBean( Class serviceClass )  {
    	

    	AutowireCapableBeanFactory factory = SpringBeanFactory.applicationContext.getAutowireCapableBeanFactory() ;
    	
    	Object serviceObject = null;
    	
    	try{

        	serviceObject = factory.getBean(serviceClass);
			
    	}catch(Exception e){
    		e.printStackTrace();
        	//System.out.println("##### ProxyFactory.createService Error");
    		//throw new Exception (e) ;
    	}
    	
    	return (T)serviceObject;
    	
    }
    
    public static <T>T getBean( String beanId ) {
    	

    	AutowireCapableBeanFactory factory = SpringBeanFactory.applicationContext.getAutowireCapableBeanFactory() ;
    	
    	Object serviceObject = null;
    	
    	try{

        	serviceObject = factory.getBean(beanId);
			
    	}catch(Exception e){
    		e.printStackTrace();
        	//System.out.println("##### ProxyFactory.createService Error");
    		//throw new Exception (e) ;
    	}
    	
    	return (T)serviceObject;
    	
    }
    

    // DomParser 생성
    public static <T>T getDomParserBean( CrawConfigBO crawConfigBO ) {
    	
    	String domParser = null;
    	
    	if (crawConfigBO != null &&  crawConfigBO.getDomParser() != null ){
    		domParser = crawConfigBO.getDomParser();
    	}	
    	
    	if ("".equals(domParser) || domParser == null) domParser =  "DomParserServiceImpl";
    	
    	return SpringBeanFactory.getBean(domParser);
    	
    }
    
    // DomParser 생성
    public static <T>T getDomParserBean(String domParser ) {

    	if ("".equals(domParser) || domParser == null) domParser =  "DomParserServiceImpl";
    	
    	return SpringBeanFactory.getBean(domParser);
    	
    }
    
    

    
    
	public void setApplicationContext(ApplicationContext applicationContext )
			throws BeansException {
		SpringBeanFactory.applicationContext = applicationContext;
	}
	
	

}
