package com.devfreaks.tripper;

import com.devfreaks.tripper.entities.User;
import com.devfreaks.tripper.repositories.UserRepository;
import com.devfreaks.tripper.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@SpringBootApplication
public class TripperApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TripperApiApplication.class, args);
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

                userService.save(user);
            }

        };

    }
}

@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    UserRepository repository;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    UserDetailsService userDetailsService() {
        return (login) -> {
            User user = repository.findByLogin(login);
            if(user != null) {
                return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), true, true, true, true,
                        AuthorityUtils.createAuthorityList("USER"));
            } else {
                throw new UsernameNotFoundException("could not find the user '"
                        + login + "'");
            }
        };
    }
}

@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();

    @Autowired
    UserDetailsService myDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .fullyAuthenticated().and()
                .httpBasic().and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // userDetailsService should be changed to your user details service
        // password encoder being the bean defined in grails-app/conf/spring/resources.groovy
        auth.userDetailsService(myDetailsService)
                .passwordEncoder(bcryptEncoder);
    }

}