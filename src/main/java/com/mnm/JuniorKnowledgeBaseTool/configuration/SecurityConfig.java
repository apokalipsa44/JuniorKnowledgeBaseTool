package com.mnm.JuniorKnowledgeBaseTool.configuration;

import com.mnm.JuniorKnowledgeBaseTool.model.UserRole;
import com.mnm.JuniorKnowledgeBaseTool.repositories.UserRepository;
import com.mnm.JuniorKnowledgeBaseTool.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private UserDetailsServiceImpl userDetailsService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsService, PasswordEncoder passwordEncoder) {
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
                .csrf().disable()
                .requestCache().requestCache(new CustomRequestCache())
                .and()
                .authorizeRequests()
                .requestMatchers(SecurityUtils::isFrameworkInternalRequest).permitAll()
                .anyRequest().hasAnyAuthority(UserRole.getAllRoles())
                /*.antMatchers("/admin").permitAll()
                .antMatchers("/myplaylists").authenticated()
                .antMatchers("/newuserform").permitAll()
                .antMatchers("/puppy").hasRole("USER")*/
                .and()
                .formLogin().loginPage("/login").permitAll().loginProcessingUrl("/login").failureUrl("/login?error")
                .successHandler(new SavedRequestAwareAuthenticationSuccessHandler())
                .and()
                .logout().logoutSuccessUrl("/");


    }
}
//@EnableGlobalMethodSecurity  -- zabezpieczenie na poziomie klas i metod


