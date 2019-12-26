package org.sprinkler.orm.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;



@Component
public class ApplicationContextHelper implements ApplicationContextAware {

	
	private static ApplicationContext applicationContext;
	private static DefaultListableBeanFactory beanFactory;
	 
    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
 
    
    public static DefaultListableBeanFactory  getBeanDefinitionRegistry() {
    	return  beanFactory;
    }

    
    //通过name获取 Bean.
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }
 
    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }
 
    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }
 
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (ApplicationContextHelper.applicationContext == null) {
        	ApplicationContextHelper.applicationContext = applicationContext;
        	ApplicationContextHelper.beanFactory=(DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        }
    }

   public static void addBean(String beanName, Object singletonObject) {
	   beanFactory.registerSingleton(beanName, singletonObject);
   }

}
