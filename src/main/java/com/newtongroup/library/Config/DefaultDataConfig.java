package com.newtongroup.library.Config;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.newtongroup.library.Bean.DefaultDataPopulatorBean;

@Configuration
public class DefaultDataConfig {

    @Bean
    public DefaultDataPopulatorBean defaultDataBean(EntityManagerFactory EntityManagerFactory){
    	return new DefaultDataPopulatorBean();
    }

}