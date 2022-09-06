package com.product.listtracker.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableJpaRepositories("com.product.listtracker.dao")
@EnableTransactionManagement // activeaza managementul de tranzactii (vezi @Transactional)
public class AppConfig {
//	  CORS config for project with Spring Security
	  @Bean
	  public FilterRegistrationBean<CorsFilter> corsFilterBean() {
			UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
			CorsConfiguration config = new CorsConfiguration();
			config.setAllowCredentials(true);

			List<String> originsPatterns = new ArrayList<>();
			originsPatterns.add("http://localhost:4200");
			config.setAllowedOriginPatterns(originsPatterns);

			config.addAllowedHeader("*");
			config.addAllowedMethod("*");
			source.registerCorsConfiguration("/**", config);
			FilterRegistrationBean<CorsFilter> bean = 
					new FilterRegistrationBean<>(new CorsFilter(source));
			bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
			return bean;
	  }
}
