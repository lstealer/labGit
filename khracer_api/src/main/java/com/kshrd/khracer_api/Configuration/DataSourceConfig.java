package com.kshrd.khracer_api.Configuration;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource myPostgresDb() {
        DataSourceBuilder dataSource = DataSourceBuilder.create();
        dataSource.driverClassName("org.postgresql.Driver");
        dataSource.url("jdbc:postgresql://35.224.54.144:5432/kh_racer_8th");
        dataSource.username("khracer");
        dataSource.password("khracer!@#");
        return dataSource.build();
    }

}
