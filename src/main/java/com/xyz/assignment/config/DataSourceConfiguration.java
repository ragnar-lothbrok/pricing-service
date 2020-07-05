package com.xyz.assignment.config;

import com.xyz.assignment.constants.ServiceConstants;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

@Configuration
@EnableJpaRepositories(basePackages = "com.xyz.assignment.jpa.repositories")
@EnableTransactionManagement
@EntityScan(basePackages = "com.xyz.assignment.jpa.entities")
@EnableConfigurationProperties
@EnableJpaAuditing
public class DataSourceConfiguration {

    /**
     * This creates Data Source.
     */
    @Bean
    @ConfigurationProperties("com.xyz.assignment.pricingservice.data-source")
    public HikariDataSource dataSource(Environment environment) {
        HikariDataSource dataSource = DataSourceBuilder.create().type(HikariDataSource.class).build();
        // NOTE: Do we have to do this ? Find out how to configure this from properties file
        final String databaseName = environment.getProperty(ServiceConstants.DB_NAME);
        if (StringUtils.isEmpty(databaseName)) {
            throw new IllegalStateException("databaseName cannot be empty/null");
        }
        dataSource.addDataSourceProperty("databaseName", databaseName);
        return dataSource;
    }
}
