package com.devfreaks.tripper;

import com.devfreaks.tripper.api.filters.JwtFilter;
import com.devfreaks.tripper.entities.User;
import com.devfreaks.tripper.entities.enums.UserRole;
import com.devfreaks.tripper.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

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
    CommandLineRunner init(final UserService userService) {

        return new CommandLineRunner() {

            @Override
            public void run(String... arg0) throws Exception {
                User user = new User();
                user.setFullName("John Doe");
                user.setActive(true);
                user.setLogin("john@doe.com");
                user.setPassword("johny");
                user.setRole(UserRole.ADMINISTRATOR);

                userService.save(user);
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
