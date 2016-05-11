package com.devfreaks.tripper.api.controllers;

import com.devfreaks.tripper.entities.User;
import com.devfreaks.tripper.exceptions.TripperException;
import com.devfreaks.tripper.exceptions.TripperUnauthorizedException;
import com.devfreaks.tripper.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class LoginController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public LoginResponse login(@RequestBody final UserLogin model) throws TripperException {
        if (model.login == null || service.findByLogin(model.login) == null) {
            throw new TripperException("Invalid login");
        }

        User user = service.findByLogin(model.login);
        if (!new BCryptPasswordEncoder().matches(model.password, user.getPassword()) || !user.getActive()) {
            throw new TripperUnauthorizedException("Wrong user/password combination.");
        }

        return new LoginResponse(Jwts.builder().setSubject(model.login)
                .claim("roles", user).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact());
    }

    @SuppressWarnings("unused")
    private static class UserLogin {
        public String login;
        public String password;
    }

    @SuppressWarnings("unused")
    private static class LoginResponse {
        public String token;

        public LoginResponse(final String token) {
            this.token = token;
        }
    }

}
