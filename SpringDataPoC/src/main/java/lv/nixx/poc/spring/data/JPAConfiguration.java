package lv.nixx.poc.spring.data;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.derby.jdbc.EmbeddedDataSource40;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.*;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("lv.nixx.poc.spring.data")
public class JPAConfiguration {

	@Bean
	public DataSource dataSource() {
		EmbeddedDataSource40 ds = new EmbeddedDataSource40();
		ds.setDatabaseName("derbyDB");
		ds.setCreateDatabase("create");
		return ds;
	}

	@Bean
	public EntityManagerFactory entityManagerFactory() {

		HibernateJpaVendorAdapter jpaAdapter = new HibernateJpaVendorAdapter();
		jpaAdapter.setDatabasePlatform("org.hibernate.dialect.DerbyTenSixDialect");
		jpaAdapter.setGenerateDdl(true);
		jpaAdapter.setShowSql(true);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource());
		factory.setJpaVendorAdapter(jpaAdapter);
		factory.setPackagesToScan("lv.nixx.poc.spring.data.domain");
		factory.afterPropertiesSet();

		return factory.getObject();
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setDataSource(dataSource());
		return txManager;
	}
	
	@Bean
	public CustomerDAO simpleDAO(){
		return new CustomerDAO();
	}

}
