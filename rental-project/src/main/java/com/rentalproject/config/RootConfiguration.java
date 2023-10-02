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
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.rentalproject.mapper.AccountMapper;
import com.rentalproject.service.AccountServiceImpl;
import com.rentalproject.service.AdminService;
import com.rentalproject.service.AdminServiceImpl;
import com.rentalproject.service.FreeBoardReviewService;
import com.rentalproject.service.FreeBoardReviewServiceImpl;
import com.rentalproject.service.FreeBoardService;
import com.rentalproject.service.FreeBoardServiceImpl;
import com.rentalproject.service.ItemServiceImpl;
import com.rentalproject.service.PrivateQnaAnswerService;
import com.rentalproject.service.PrivateQnaAnswerServiceImpl;
import com.rentalproject.service.PrivateQnaService;
import com.rentalproject.service.PrivateQnaServiceImpl;
import com.rentalproject.service.ProfileServiceImpl;

@Configuration
@MapperScan(basePackages = {"com.rentalproject.mapper"})
@EnableTransactionManagement
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
	
 
	@Bean // JDBC template
	public JdbcTemplate jdbcTemplate () {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dbcpDataSource());                       
		
		return jdbcTemplate;
	}
	
		
		
		
	@Bean // 계정 관련
	public AccountServiceImpl accountService() {
		AccountServiceImpl accountService = new AccountServiceImpl();
		accountService.setAccountMapper(applicationcontext.getBean(AccountMapper.class)); // setter 방식
		
		return accountService;
	}
	
	@Bean // 프로필
	public ProfileServiceImpl profileService() {
		ProfileServiceImpl profileService = new ProfileServiceImpl();
		return profileService;
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
	public PrivateQnaService privateQnaService() {
		PrivateQnaService privateQnaService = new PrivateQnaServiceImpl();
		
		return privateQnaService;
		
	}
	
	@Bean
	public FreeBoardService freeboardService () {
		FreeBoardService freeboardService = new FreeBoardServiceImpl ();
		return freeboardService;
	}
	
	@Bean
	public FreeBoardReviewService freeboardReviewService () {
		FreeBoardReviewService freeboardReviewService = new FreeBoardReviewServiceImpl ();
		return freeboardReviewService;
	}
	
	@Bean
	public ItemServiceImpl itemService() {
		ItemServiceImpl itemService = new ItemServiceImpl();
		
		return itemService;
	}
	
	@Bean
	public PrivateQnaAnswerService privateQnaAnserService() {
		PrivateQnaAnswerService  privateQnaAnswerService= new PrivateQnaAnswerServiceImpl();
		return privateQnaAnswerService;
	}
	
	@Bean
	public AdminService adminService() {
		AdminServiceImpl adminService = new AdminServiceImpl();
		return adminService;
	}
	
	// 트랜잭션 설정
	@Bean
	public DataSourceTransactionManager txManager() {
		return new DataSourceTransactionManager(dbcpDataSource());
	}
	
}
