package com.tht.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService myUserDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(){
        return new CustomLoginFailureHandler();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(myUserDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity https) throws Exception {
        https   .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/manager**","/courses**","/programme**","lesson**").hasAnyAuthority("ROLE_ADMIN","ROLE_MINISTRY","ROLE_TEACHER")
                .antMatchers("/blogManager**","/programme**","/courses**","/lesson**","/exampleType**","/student**","/mail").hasAnyAuthority("ROLE_ADMIN","ROLE_MINISTRY")
                .antMatchers("/staff**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/profile**","/timetable**").hasAnyAuthority("ROLE_ADMIN","ROLE_MINISTRY","ROLE_TEACHER","ROLE_STUDENT")
                .antMatchers("/user").hasRole("USER")
                .antMatchers("/api/user").permitAll()
                .and()
                .formLogin()
                .loginPage("/login/")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/blogPage")
                .failureHandler(authenticationFailureHandler())
                .and()
                .exceptionHandling().accessDeniedPage("/login/login")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me")
                .logoutSuccessUrl("/blogPage");
    }
}
