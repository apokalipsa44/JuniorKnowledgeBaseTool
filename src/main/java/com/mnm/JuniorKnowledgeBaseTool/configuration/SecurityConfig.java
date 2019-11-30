package com.mnm.JuniorKnowledgeBaseTool.configuration;

import com.mnm.JuniorKnowledgeBaseTool.model.User;
import com.mnm.JuniorKnowledgeBaseTool.model.UserRole;
import com.mnm.JuniorKnowledgeBaseTool.repositories.UserRepository;
import com.mnm.JuniorKnowledgeBaseTool.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public User currentUser(UserRepository userRepository) {
        return userRepository.findByUsername(SecurityUtils.getUsername());
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
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
/*
                .requestMatchers(SecurityUtils::isFrameworkInternalRequest).permitAll()
                //.anyRequest().hasAnyAuthority(UserRole.getAllRoles())
                /*.antMatchers("/admin").permitAll()
                .antMatchers("/myplaylists").authenticated()

 */
                .antMatchers("/admin").permitAll()
                .antMatchers("/myplaylists").permitAll()

                .antMatchers("/newuserform").permitAll()
                .antMatchers("/puppy").hasRole("ADMIN")
                .and()
                .formLogin().usernameParameter("username").loginPage("/login").permitAll()
                .and()
                .logout().logoutSuccessUrl("/");


    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                // Vaadin Flow static resources
                "/VAADIN/**",

                // the standard favicon URI
                "/favicon.ico",

                // web application manifest
                "/manifest.json",
                "/sw.js",
                "/offline-page.html",

                // icons and images
                "/icons/**",
                "/images/**",

                // (development mode) static resources
                "/frontend/**",

                // (development mode) webjars
                "/webjars/**",

                // (development mode) H2 debugging console
                "/h2-console/**",

                // (production mode) static resources
                "/frontend-es5/**", "/frontend-es6/**");
    }
}
//@EnableGlobalMethodSecurity  -- zabezpieczenie na poziomie klas i metod


