package com.rentalproject.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.rentalproject.mapper.AccountMapper;
import com.rentalproject.service.AccountServiceImpl;
import com.rentalproject.service.FreeBoardService;
import com.rentalproject.service.FreeBoardServiceImpl;

import lombok.Setter;

@Configuration
@MapperScan(basePackages = {"com.rentalproject.mapper"})
public class RootConfiguration implements ApplicationContextAware{
	
	
	private ApplicationContext applicationcontext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		
		this.applicationcontext = applicationContext;
	}
	
	@Bean
	public BasicDataSource dbcpDataSource( ) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://3.39.195.155:3306/rentalsite");
		dataSource.setUsername("rental");
		dataSource.setPassword("rental");
		dataSource.setMaxTotal(10);
		dataSource.setMaxIdle(5);
		dataSource.setMaxWaitMillis(-1);
		
		return dataSource;
	}
	
	// JDBC template 
		@Bean
		public JdbcTemplate jdbcTemplate () {
			JdbcTemplate jdbcTemplate = new JdbcTemplate();
			jdbcTemplate.setDataSource(dbcpDataSource());                       
			
			return jdbcTemplate;
		}
	
	@Bean
	public AccountServiceImpl accountService() {
		AccountServiceImpl accountService = new AccountServiceImpl();
		accountService.setAccountMapper(applicationcontext.getBean(AccountMapper.class));
		
		return accountService;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();  // 다른 객체를 만드는 객체 
		factoryBean.setDataSource(dbcpDataSource());
		factoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
		SqlSessionFactory sessionFactory = factoryBean.getObject(); // IoC컨테이너에 등록할 객체 생성
		
		return sessionFactory;
	}
	
	
	@Bean
	public FreeBoardService freeboardService () {
		FreeBoardService freeboardService = new FreeBoardServiceImpl ();
		return freeboardService;
	}
	
}
