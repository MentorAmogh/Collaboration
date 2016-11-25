package com.collaborationserver.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.collaborationserver.dao.UserServiceImpl;
import com.collaborationserver.model.BlogComment;
import com.collaborationserver.model.BlogPost;
import com.collaborationserver.model.Forum;
import com.collaborationserver.model.ForumComment;
import com.collaborationserver.model.Users;
import com.collaborationserver.service.UserService;

@Configuration
@ComponentScan("com.collaborationserver")
@EnableTransactionManagement
public class ApplicationContextConfig {

	@Bean(name = "dataSource")
	public DataSource getOracleDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		System.out.println("driver class found...!!");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:ORCL");		
		dataSource.setUsername("saa");
		dataSource.setPassword("P_ssw0rd");
		System.out.println("database connected");

		Properties connectionProperties = new Properties();

		connectionProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		dataSource.setConnectionProperties(connectionProperties);
		return dataSource;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		// sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClass(Users.class);

		sessionBuilder.addAnnotatedClass(BlogPost.class);
		sessionBuilder.addAnnotatedClass(BlogComment.class);

		sessionBuilder.addAnnotatedClass(Forum.class);
		sessionBuilder.addAnnotatedClass(ForumComment.class);

		return sessionBuilder.buildSessionFactory();
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}

	@Autowired
	@Bean(name = "userServiceDAO")
	public UserService getUserDetailsDAO(SessionFactory sessionFactory) {
		return new UserServiceImpl(sessionFactory);
	}

}
