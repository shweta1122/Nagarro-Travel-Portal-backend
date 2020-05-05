package com.nagarro.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig { // implements Filter {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("*");

            }
        };
    }
}

// @Override
// public void doFilter(ServletRequest req, ServletResponse res, FilterChain
// chain)
// throws IOException, ServletException {

// HttpServletRequest request = (HttpServletRequest) req;
// HttpServletResponse response = (HttpServletResponse) res;

// response.setHeader("Access-Control-Allow-Origin",
// request.getHeader("Origin"));
// response.setHeader("Access-Control-Allow-Credentials", "true");
// response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS,
// DELETE");
// response.setHeader("Access-Control-Max-Age", "3600");
// response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept,
// X-Requested-With, remember-me");

// chain.doFilter(req, res);
// }
// }}
