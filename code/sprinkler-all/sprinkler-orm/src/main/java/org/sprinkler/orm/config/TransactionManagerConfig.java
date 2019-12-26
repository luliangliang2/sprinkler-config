package org.sprinkler.orm.config;

import javax.transaction.UserTransaction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;

@Configuration
@EnableTransactionManagement
@Order(100)
public class TransactionManagerConfig {
    @Bean(name = "userTransaction")
    public UserTransaction userTransaction() throws Throwable {
	UserTransactionImp userTransactionImp = new UserTransactionImp();
	userTransactionImp.setTransactionTimeout(10000);
	return userTransactionImp;
    }

    @Bean(name = "atomikosTransactionManager", initMethod = "init", destroyMethod = "close")
    public UserTransactionManager atomikosTransactionManager() throws Throwable {
	UserTransactionManager userTransactionManager = new UserTransactionManager();
	userTransactionManager.setForceShutdown(false);
	return userTransactionManager;
    }

    @Bean(name = "transactionManager")
    @DependsOn({ "userTransaction", "atomikosTransactionManager" })
    public JtaTransactionManager transactionManager() throws Throwable {
	UserTransaction userTransaction = userTransaction();
	JtaTransactionManager manager = new JtaTransactionManager(userTransaction, atomikosTransactionManager());
	return manager;
    }
}
