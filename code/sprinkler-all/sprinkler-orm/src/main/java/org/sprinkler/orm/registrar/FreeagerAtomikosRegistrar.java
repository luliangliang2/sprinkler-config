package org.sprinkler.orm.registrar;

import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;
import org.sprinkler.orm.annotations.EnableFreeagerAtomikos;


import tk.mybatis.spring.mapper.MapperScannerConfigurer;

public class FreeagerAtomikosRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware, BeanFactoryAware {
	private static Logger log = Logger.getLogger(FreeagerAtomikosRegistrar.class); 
	
    private Environment environment;
    private BeanFactory beanFactory;

    @Override
    public void setEnvironment(Environment environment) {
	this.environment = environment;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
	this.beanFactory = beanFactory;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
	Map<String, Object> map = importingClassMetadata.getAnnotationAttributes(EnableFreeagerAtomikos.class.getName());
	String[] dsNames = (String[]) map.get("dataSource");
	boolean hasDefault = false;
	for (String dsName : dsNames) {
	    Properties prop = build(this.environment, "freeager.atomikos." + dsName + ".");

	    String def = environment.getProperty("freeager.atomikos." + dsName + ".isDefault");

	    GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
	    beanDefinition.setBeanClass(AtomikosDataSourceBean.class);
	    MutablePropertyValues propertyValues = new MutablePropertyValues();
	    propertyValues.add("xaDataSourceClassName", "com.alibaba.druid.pool.xa.DruidXADataSource");
	    propertyValues.add("uniqueResourceName", dsName);
	    propertyValues.add("poolSize", 5);
	    propertyValues.add("xaProperties", prop);
	    beanDefinition.setPropertyValues(propertyValues);
	    beanDefinition.setSynthetic(true);
	    if (!hasDefault && "true".equals(def)) {
		hasDefault = true;
		beanDefinition.setPrimary(true);
	    }
	    registry.registerBeanDefinition(dsName, beanDefinition);
	    log.info("配置了数据源：{"+dsName+"},{"+def+"}" );

	    beanDefinition = new GenericBeanDefinition();
	    beanDefinition.setBeanClass(SqlSessionFactoryBean.class);
	    propertyValues = new MutablePropertyValues();
	    AtomikosDataSourceBean ds = (AtomikosDataSourceBean) beanFactory.getBean(dsName);
	    propertyValues.addPropertyValue("dataSource", ds);
	    beanDefinition.setPropertyValues(propertyValues);
	    String factoryName = dsName + "Factory";
	    registry.registerBeanDefinition(factoryName, beanDefinition);
	    log.info("配置了会话工厂：{"+factoryName+"}");

	    beanDefinition = new GenericBeanDefinition();
	    beanDefinition.setBeanClass(SqlSessionTemplate.class);
	    ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
	    SqlSessionFactory factory = (SqlSessionFactory) beanFactory.getBean(factoryName);
	    constructorArgumentValues.addIndexedArgumentValue(0, factory);
	    beanDefinition.setConstructorArgumentValues(constructorArgumentValues);
	    String templateName = dsName + "Template";
	    registry.registerBeanDefinition(templateName, beanDefinition);
	    log.info("配置了{"+templateName+"}");

	    beanDefinition = new GenericBeanDefinition();
	    beanDefinition.setBeanClass(MapperScannerConfigurer.class);
	    propertyValues = new MutablePropertyValues();
	    String basePackage = environment.getProperty("freeager.atomikos." + dsName + ".basePackage");
	    Assert.hasText(basePackage, String.format("必须配置%s的basePackage", dsName));
	    propertyValues.addPropertyValue("basePackage", basePackage);
	    propertyValues.addPropertyValue("sqlSessionTemplateBeanName", templateName);
	    beanDefinition.setPropertyValues(propertyValues);
	    registry.registerBeanDefinition(dsName + "MapperScanner", beanDefinition);
	    log.info("配置了{"+dsName+"}的MapperScanner" );
	}
	Assert.isTrue(hasDefault, "必须设置默认的数据源(例：freeager.atomikos.myDS.isDefault=true)");
    }

    private Properties build(Environment env, String prefix) {
	Properties prop = new Properties();
	prop.put("url", env.getProperty(prefix + "url"));
	prop.put("username", env.getProperty(prefix + "username"));
	prop.put("password", env.getProperty(prefix + "password"));
	prop.put("driverClassName", env.getProperty(prefix + "driverClassName", ""));
	prop.put("initialSize", env.getProperty(prefix + "initialSize", Integer.class));
	prop.put("maxActive", env.getProperty(prefix + "maxActive", Integer.class));
	prop.put("minIdle", env.getProperty(prefix + "minIdle", Integer.class));
	prop.put("maxWait", env.getProperty(prefix + "maxWait", Integer.class));
	prop.put("poolPreparedStatements", env.getProperty(prefix + "poolPreparedStatements", Boolean.class));
	prop.put("maxPoolPreparedStatementPerConnectionSize",
		env.getProperty(prefix + "maxPoolPreparedStatementPerConnectionSize", Integer.class));
	prop.put("maxPoolPreparedStatementPerConnectionSize",
		env.getProperty(prefix + "maxPoolPreparedStatementPerConnectionSize", Integer.class));
	prop.put("validationQuery", env.getProperty(prefix + "validationQuery"));
	prop.put("validationQueryTimeout", env.getProperty(prefix + "validationQueryTimeout", Integer.class));
	prop.put("testOnBorrow", env.getProperty(prefix + "testOnBorrow", Boolean.class));
	prop.put("testOnReturn", env.getProperty(prefix + "testOnReturn", Boolean.class));
	prop.put("testWhileIdle", env.getProperty(prefix + "testWhileIdle", Boolean.class));
	prop.put("timeBetweenEvictionRunsMillis",
		env.getProperty(prefix + "timeBetweenEvictionRunsMillis", Integer.class));
	prop.put("minEvictableIdleTimeMillis", env.getProperty(prefix + "minEvictableIdleTimeMillis", Integer.class));
	prop.put("filters", env.getProperty(prefix + "filters"));
	return prop;
    }

}
