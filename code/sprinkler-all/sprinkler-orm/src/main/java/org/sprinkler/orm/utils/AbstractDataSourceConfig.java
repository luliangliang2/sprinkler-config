package org.sprinkler.orm.utils;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.core.env.Environment;

import com.atomikos.jdbc.AtomikosDataSourceBean;

public class AbstractDataSourceConfig {

	protected DataSource getDataSource(Environment env, String prefix, String dataSourceName) {
		Properties prop = build(env, prefix);
		AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
		ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
		ds.setUniqueResourceName(dataSourceName);
		ds.setXaProperties(prop);
		return ds;
	}
	protected DataSource getDataSource(Properties env, String prefix, String dataSourceName) {
		Properties prop = build(env, prefix);
		AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
		ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
		ds.setUniqueResourceName(dataSourceName);
		ds.setXaProperties(prop);
		return ds;
	}

	protected Properties build(Properties env, String prefix) {
		Properties prop = new Properties();
		prop.put("url", env.getProperty(prefix + "url"));
		prop.put("username", env.getProperty(prefix + "username"));
		prop.put("password", env.getProperty(prefix + "password"));
		prop.put("driverClassName", env.getProperty(prefix + "driverClassName", ""));
		prop.put("initialSize", env.getProperty(prefix + "maxWait")==null?5:Integer.valueOf(env.getProperty(prefix + "initialSize")));
		prop.put("maxActive", env.getProperty(prefix + "maxActive")==null?5:Integer.valueOf(env.getProperty(prefix + "maxActive")));
		prop.put("minIdle", env.getProperty(prefix + "minIdle")==null?5:Integer.valueOf(env.getProperty(prefix + "minIdle")));
		prop.put("maxWait", env.getProperty(prefix + "maxWait")==null?5:Integer.valueOf(env.getProperty(prefix + "maxWait")));
		prop.put("poolPreparedStatements", env.getProperty(prefix + "poolPreparedStatements")==null?false:Boolean.valueOf(env.getProperty(prefix + "poolPreparedStatements")));
		prop.put("maxPoolPreparedStatementPerConnectionSize",
				 env.getProperty(prefix + "maxPoolPreparedStatementPerConnectionSize")==null?5:
				Integer.valueOf(env.getProperty(prefix + "maxPoolPreparedStatementPerConnectionSize")));
		prop.put("validationQuery",env.getProperty(prefix + "validationQueryTimeout")==null?"": env.getProperty(prefix + "validationQuery"));
		prop.put("validationQueryTimeout",env.getProperty(prefix + "validationQueryTimeout")==null?1000: Integer.valueOf(env.getProperty(prefix + "validationQueryTimeout")));
		prop.put("testOnBorrow", env.getProperty(prefix + "testOnBorrow")==null?false:Boolean.valueOf(env.getProperty(prefix + "testOnBorrow")));
		prop.put("testOnReturn", env.getProperty(prefix + "testOnReturn")==null?false:Boolean.valueOf(env.getProperty(prefix + "testOnReturn")));
		prop.put("testWhileIdle",env.getProperty(prefix + "testWhileIdle")==null?false: Boolean.valueOf(env.getProperty(prefix + "testWhileIdle")));
		prop.put("timeBetweenEvictionRunsMillis",
				env.getProperty(prefix + "timeBetweenEvictionRunsMillis")==null?5000:
				Integer.valueOf(env.getProperty(prefix + "timeBetweenEvictionRunsMillis")));
		prop.put("minEvictableIdleTimeMillis",env.getProperty(prefix + "minEvictableIdleTimeMillis")==null?5000: Integer.valueOf(env.getProperty(prefix + "minEvictableIdleTimeMillis")));
		prop.put("filters",env.getProperty(prefix + "filters")==null?"": env.getProperty(prefix + "filters"));
		return prop;
	}

	protected Properties build(Environment env, String prefix) {
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
