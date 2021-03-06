package com.devfreaks.tripper.api.filters;

import com.devfreaks.tripper.exceptions.TripperUnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {

    @Override
    public void doFilter(final ServletRequest req,
                         final ServletResponse res,
                         final FilterChain chain) throws IOException, TripperUnauthorizedException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final String authHeader = request.getHeader("Authorization");

        if (
                (!request.getRequestURI().startsWith("/api/airports") && !request.getRequestURI().startsWith("/api/search"))
                        && (authHeader == null || !authHeader.startsWith("Bearer "))
                ) {
            throw new TripperUnauthorizedException("Missing or invalid Authorization header.");
        }

        if (!request.getRequestURI().startsWith("/api/search") && !request.getRequestURI().startsWith("/api/airports")) {
            final String token = authHeader.substring(7); // The part after "Bearer "

            try {
                final Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
                request.setAttribute("claims", claims);
            } catch (final SignatureException e) {
                throw new TripperUnauthorizedException("Invalid token.");
            }
        }

        chain.doFilter(req, res);
    }

}
