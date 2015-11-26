package cz.muni.fi.pa165.facade.test;

import static org.mockito.Mockito.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import cz.muni.fi.pa165.config.ServiceConfiguration;
import cz.muni.fi.pa165.service.BookService;
import cz.muni.fi.pa165.service.LoanService;
import cz.muni.fi.pa165.service.MemberService;

/**
 * Test configuration to create mocks for facade testing. InjectMocks doesn't
 * work on Transactional object because of proxying
 *
 * @author Michael Simacek
 *
 */
@Configuration
@Import(ServiceConfiguration.class)
public class FacadeTestConfiguration {
    @Bean
    @Primary
    public LoanService loanService() {
        return mock(LoanService.class);
    }

    @Bean
    @Primary
    public BookService bookService() {
        return mock(BookService.class);
    }

    @Bean
    @Primary
    public MemberService memberService() {
        return mock(MemberService.class);
    }
}
