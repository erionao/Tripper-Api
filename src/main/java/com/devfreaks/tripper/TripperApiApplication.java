package com.devfreaks.tripper;

import com.devfreaks.tripper.api.filters.JwtFilter;
import com.devfreaks.tripper.dump.DataDump;
import com.devfreaks.tripper.entities.Airport;
import com.devfreaks.tripper.entities.Country;
import com.devfreaks.tripper.entities.QCountry;
import com.devfreaks.tripper.entities.User;
import com.devfreaks.tripper.entities.enums.UserRole;
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
    CommandLineRunner init(final ResourceLoader resourceLoader, final UserService userService, final CountryService countryService, final AirportService airportService) {

        return new CommandLineRunner() {

            @Override
            public void run(String... arg0) throws Exception {
                //DataDump.seed(resourceLoader, userService, countryService, airportService);
            }

        };
    }

    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.addUrlPatterns("/api/*");

        return registrationBean;
    }
}
