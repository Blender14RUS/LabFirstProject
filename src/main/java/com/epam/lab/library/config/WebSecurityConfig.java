package com.epam.lab.library.config;

import com.epam.lab.library.dao.UserDao;
import com.epam.lab.library.domain.AccessLevel;
import com.epam.lab.library.service.impl.DataBaseUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.epam.lab.library.domain.AccessLevel.ADMIN;
import static com.epam.lab.library.domain.AccessLevel.LIBRARIAN;
import static com.epam.lab.library.domain.AccessLevel.READER;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDao userDao;

    @Autowired
    WebSecurityConfig (UserDao userDao) {this.userDao = userDao; }

    @Autowired
    DataBaseUserDetailService dataBaseUserDetailService;

    @Bean
    public PasswordEncoder passwordEncoder(){return PasswordEncoderFactories.createDelegatingPasswordEncoder();}

    @Override
    protected void configure (HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/registration", "/books", "/",
                        "/registration-failure", "/resources/**").permitAll()
                .antMatchers("/admin/**").hasRole(ADMIN.toString())
                .antMatchers("/lib/**").hasAnyRole(LIBRARIAN.toString(), ADMIN.toString())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").defaultSuccessUrl("/books", true)
                .permitAll()
                .and()
                .logout().logoutUrl("/auth/logout").
                logoutSuccessUrl("/login")
                .permitAll();


    }



}
