package com.devfreaks.tripper;

import com.devfreaks.tripper.api.filters.JwtFilter;
import com.devfreaks.tripper.services.AirportService;
import com.devfreaks.tripper.services.CountryService;
import com.devfreaks.tripper.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ResourceLoader;

@SpringBootApplication
public class TripperApiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(TripperApiApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TripperApiApplication.class);
    }

    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.addUrlPatterns("/api/*");

        return registrationBean;
    }
}
