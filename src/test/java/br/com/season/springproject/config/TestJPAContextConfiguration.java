package br.com.season.springproject.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@ComponentScan(basePackages = {"br.com.season.springproject.entity",
		"br.com.season.springproject.dao",
		"br.com.season.springproject.service"})
public class TestJPAContextConfiguration {
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
		LocalContainerEntityManagerFactoryBean lcef = new LocalContainerEntityManagerFactoryBean();
		lcef.setDataSource(dataSource());
		lcef.setPackagesToScan(new String [] {"br.com.season.springproject"});
		lcef.setPersistenceUnitName("testPU");
		
		HibernateJpaVendorAdapter va = new HibernateJpaVendorAdapter();
		lcef.setJpaVendorAdapter(va);
		
		Properties ps = new Properties();
		ps.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		ps.put("hibernate.hbm2ddl.auto","create");
		lcef.setJpaProperties(ps);
		lcef.afterPropertiesSet();
		
		return lcef;
	}

	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		builder.setType(EmbeddedDatabaseType.H2);
		return builder.build();
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(){
		JpaTransactionManager tm = new JpaTransactionManager();
		tm.setEntityManagerFactory(this.entityManagerFactoryBean().getObject());
		return tm;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	
	
}
