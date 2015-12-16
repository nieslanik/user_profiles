package cz.muni.fi.pa165.config;

import javax.inject.Inject;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import cz.muni.fi.pa165.security.MemberUserDetailsService;

@EnableWebSecurity
@Import(ServiceConfiguration.class)
@ComponentScan("cz.muni.fi.pa165.security")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Inject
    private MemberUserDetailsService userDetailsService;

    @Inject
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/webjars/**").permitAll()
        .anyRequest().authenticated()
        .and().formLogin().loginPage("/login").permitAll();
    }
}
