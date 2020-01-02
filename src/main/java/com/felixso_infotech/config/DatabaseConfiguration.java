package com.felixso_infotech.config;

import io.github.jhipster.config.JHipsterConstants;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "mysqlTransactionManager", basePackages = "com.felixso_infotech.repository")
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
@EnableTransactionManagement
public class DatabaseConfiguration {

	private final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);
	private final Environment env;

	public DatabaseConfiguration(Environment env) {
		this.env = env;
	}

	/*
	 * @Primary
	 * 
	 * @Bean(name = "dataSource")
	 * 
	 * @ConfigurationProperties(prefix = "spring.datasource") public DataSource
	 * dataSource() { return DataSourceBuilder.create().build(); }
	 */

	@Bean(name = "dataSource")
	@ConfigurationProperties(prefix="spring.datasource")
	public HikariDataSource dataSource(DataSourceProperties properties) {
		log.debug("creating hikari data source {}",properties);
		return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}

	@Primary
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("dataSource") DataSource dataSource) {

		return builder.dataSource(dataSource).packages("com.felixso_infotech.domain").build();
	}

	@Primary
	@Bean(name = "mysqlTransactionManager")
	public JpaTransactionManager mysqlTransactionManager(
			@Qualifier("entityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {

		return new JpaTransactionManager(entityManagerFactory.getObject());
	}

	@Autowired
	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(Neo4jTransactionManager neo4jTransactionManager,
			JpaTransactionManager mysqlTransactionManager) {
		log.debug("Initializing platform transaction manager");
		return new ChainedTransactionManager(mysqlTransactionManager, neo4jTransactionManager);
	}
}
