package com.rentalproject.config;

import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.rentalproject.mapper.AccountMapper;
import com.rentalproject.service.AccountServiceImpl;
import com.rentalproject.service.AdminService;
import com.rentalproject.service.AdminServiceImpl;
import com.rentalproject.service.FreeBoardRecommandService;
import com.rentalproject.service.FreeBoardRecommandServiceImpl;
import com.rentalproject.service.FreeBoardReportService;
import com.rentalproject.service.FreeBoardReportServiceImpl;
import com.rentalproject.service.FreeBoardReviewService;
import com.rentalproject.service.FreeBoardReviewServiceImpl;
import com.rentalproject.service.FreeBoardService;
import com.rentalproject.service.FreeBoardServiceImpl;
import com.rentalproject.service.ItemQnaService;
import com.rentalproject.service.ItemQnaServiceImpl;

import com.rentalproject.service.ItemReviewService;
import com.rentalproject.service.ItemReviewServiceImpl;

import com.rentalproject.service.ItemServiceImpl;
import com.rentalproject.service.KakaoService;
import com.rentalproject.service.NoticeService;
import com.rentalproject.service.NoticeServiceImpl;
import com.rentalproject.service.OrderServiceImpl;
import com.rentalproject.service.PrivateQnaAnswerService;
import com.rentalproject.service.PrivateQnaAnswerServiceImpl;
import com.rentalproject.service.PrivateQnaService;
import com.rentalproject.service.PrivateQnaServiceImpl;
import com.rentalproject.service.ProfileServiceImpl;
import com.rentalproject.service.VisitServiceImpl;
import com.rentalproject.service.ZzimServiceImpl;

@Configuration
@MapperScan(basePackages = {"com.rentalproject.mapper"})
@ComponentScan(basePackages = {"com.rentalproject.dao"})
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
	public FreeBoardReportService freeboardReportService () {
		FreeBoardReportService freeboardReportService = new FreeBoardReportServiceImpl ();
		return freeboardReportService;
	}
	
	@Bean
	public FreeBoardRecommandService freeboardRecommandService () {
		FreeBoardRecommandService freeboardRecommandService = new FreeBoardRecommandServiceImpl ();
		return freeboardRecommandService;
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
	public ItemReviewService itemReviewService () {
		ItemReviewService itemReviewService = new ItemReviewServiceImpl ();
		return itemReviewService;
	}
 
 
	@Bean 
	public OrderServiceImpl orderService() {
		OrderServiceImpl orderService = new OrderServiceImpl();
		
		return orderService; 
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
	@Bean
	public NoticeService noticeService() {
		NoticeServiceImpl noticeService = new NoticeServiceImpl();
		return noticeService;
	}
	
	
	// 이메일 설정
	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.naver.com");
        mailSender.setPort(465);
        mailSender.setUsername("rlrkxks35");
        mailSender.setPassword("ekrxj35+2=7");
        
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtps.checkserveridentity", "true");
        properties.put("mail.smtps.ssl.trust", "*");
        properties.put("mail.debug", "true");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // gmail의 경우 보안문제 업데이트로 인해 SSLSocketFactory를 추가해야 smtp 사용 가능
        
        
//      properties.put("mail.smtp.ssl.trust", "smtp.naver.com");
//      properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        mailSender.setJavaMailProperties(properties);

        return mailSender;
        
	}
	
	@Bean
	public KakaoService ks() {
		KakaoService ks = new KakaoService();
		return ks;
	}
	
	@Bean
	public ZzimServiceImpl zzimService() {
		ZzimServiceImpl zzimService = new ZzimServiceImpl();
		return zzimService;
	}
	
	@Bean

	public VisitServiceImpl visitService() {
		VisitServiceImpl visitService = new VisitServiceImpl();
		return visitService;
	}
	
	@Bean

	public ItemQnaService itemQnaService() {
		
		ItemQnaService itemQnaService = new ItemQnaServiceImpl();
		
		return itemQnaService;
		
		
	}
	
}
