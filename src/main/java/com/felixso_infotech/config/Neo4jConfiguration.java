package com.felixso_infotech.config;

import org.neo4j.ogm.session.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.felixso_infotech.domain.graph")
@EnableNeo4jRepositories(sessionFactoryRef = "getSessionFactory", transactionManagerRef = "graphTransactionManager", basePackages = "com.felixso_infotech.repository.graph")
@EnableTransactionManagement
public class Neo4jConfiguration {
	private final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);

	@Value("${spring.data.neo4j.uri}")
	private String databaseUrl;

	@Value("${spring.data.neo4j.username}")
	private String userName;

	@Value("${spring.data.neo4j.password}")
	private String password;

	@Bean(name = "getSessionFactory")
	public SessionFactory sessionFactory() {
		org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration.Builder()
				.uri(databaseUrl).credentials(userName, password).build();
		// with domain entity base package(s)
		return new SessionFactory(configuration, "com.felixso_infotech.domain.graph");
	}

	@Bean(name = "graphTransactionManager")
	public Neo4jTransactionManager transactionManager(@Qualifier("getSessionFactory") SessionFactory sessionFactory) {
		return new Neo4jTransactionManager(sessionFactory());
	}
}
