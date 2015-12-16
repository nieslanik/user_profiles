package cz.muni.fi.pa165.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import cz.muni.fi.pa165.spring.LibrarySpringContext;

/**
 * Configuration od services
 * 
 * @author Michael Šimáček
 */

@Configuration
@Import(LibrarySpringContext.class)
@ComponentScan({ "cz.muni.fi.pa165.service", "cz.muni.fi.pa165.facade" })
public class ServiceConfiguration {
    @Bean
    public Mapper mapper() {
        return new DozerBeanMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
