package com.summer.xblog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Profile("pro")
@Configuration
@EnableWebSecurity
public class ProSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthSuccessHandler successHandler;
    @Autowired
    private AuthFailureHandler failHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/swagger-ui.html").authenticated()
                .anyRequest().permitAll().and()
                .formLogin().loginPage("/api/login").permitAll().successHandler(successHandler).failureHandler(failHandler).and()
                .logout().logoutUrl("/api/logout").and()
                .sessionManagement().maximumSessions(1).expiredUrl("/api/login");
        http.csrf().disable().headers().frameOptions().sameOrigin();    //iframe同源允许链接
        http.cors().disable();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
