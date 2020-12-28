package com.vernum.gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    UserDetailsService  userDetailsService;
    PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfiguration(@Qualifier("vernum") UserDetailsService  userDetailsService,
                                 PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                .and()
                    .authorizeRequests()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/api/**").hasRole("ADMIN")
                .and()
                    .formLogin()
                        .usernameParameter("username")
                        .passwordParameter("password")
                .and()
                .csrf().disable();
    }
}
