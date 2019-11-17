package com.mnm.JuniorKnowledgeBaseTool.configuration;

import com.mnm.JuniorKnowledgeBaseTool.repositories.UserRepository;
import com.mnm.JuniorKnowledgeBaseTool.services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin").permitAll()
                .antMatchers("/myplaylists").authenticated()
                .antMatchers("/newuserform").permitAll()
                .antMatchers("/puppy").hasRole("USER")
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll();


    }
}
//@EnableGlobalMethodSecurity  -- zabezpieczenie na poziomie klas i metod


