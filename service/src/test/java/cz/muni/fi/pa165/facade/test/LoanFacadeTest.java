package cz.muni.fi.pa165.facade.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Date;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cz.muni.fi.pa165.dto.BookDTO;
import cz.muni.fi.pa165.dto.CreateLoanDTO;
import cz.muni.fi.pa165.dto.LoanDTO;
import cz.muni.fi.pa165.dto.MemberDTO;
import cz.muni.fi.pa165.entity.Book;
import cz.muni.fi.pa165.entity.Loan;
import cz.muni.fi.pa165.entity.Member;
import cz.muni.fi.pa165.enums.BookState;
import cz.muni.fi.pa165.facade.LoanFacade;
import cz.muni.fi.pa165.service.BookService;
import cz.muni.fi.pa165.service.LoanService;
import cz.muni.fi.pa165.service.MemberService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FacadeTestConfiguration.class)
public class LoanFacadeTest {
    // mock injected from FacadeTestConfiguration
    @Inject
    LoanService loanServiceMock;
    @Inject
    MemberService memberServiceMock;
    @Inject
    BookService bookServiceMock;

    @Inject
    LoanFacade facade;

    @Test
    public void testCreate() {
        ArgumentCaptor<Loan> captor = ArgumentCaptor.forClass(Loan.class);
        Member member = new Member();
        member.setId(1L);
        Book book = new Book();
        book.setId(1L);
        when(memberServiceMock.findById(1L)).thenReturn(member);
        when(bookServiceMock.findById(1L)).thenReturn(book);
        CreateLoanDTO loan = new CreateLoanDTO();
        loan.setBookId(1L);
        loan.setMemberId(1L);
        facade.createLoan(loan);
        verify(loanServiceMock).create(captor.capture());
        Loan entity = captor.getValue();
        assertNull(entity.getId());
        assertSame(member, entity.getMember());
        assertSame(book, entity.getBook());
    }

    @Test
    public void testReturnLoan() {
        facade.returnLoan(1L, BookState.HEAVY_DAMAGE);
        verify(loanServiceMock).returnLoan(1L, BookState.HEAVY_DAMAGE);
    }

    @Test
    public void testFindById() {
        Date d = new Date(0);
        Book book = new Book();
        book.setId(1L);
        Member member = new Member();
        member.setId(2L);
        Loan loan = new Loan();
        loan.setId(3L);
        loan.setBook(book);
        loan.setMember(member);
        loan.setReturnBookState(BookState.HEAVY_DAMAGE);
        loan.setLoanDate(d);
        loan.setReturnDate(d);
        when(loanServiceMock.findById(3L)).thenReturn(loan);
        LoanDTO dto = facade.findById(3L);
        assertEquals(Long.valueOf(3), dto.getId());
        BookDTO bookDto = dto.getBook();
        assertNotNull(bookDto);
        assertEquals(Long.valueOf(1), bookDto.getId());
        MemberDTO memberDto= dto.getMember();
        assertNotNull(memberDto);
        assertEquals(Long.valueOf(2), memberDto.getId());
        assertEquals(BookState.HEAVY_DAMAGE, dto.getReturnBookState());
        assertEquals(d, dto.getLoanDate());
        assertEquals(d, dto.getReturnDate());
    }

    @Test
    public void testFindAll() {

    }

    @Test
    public void testDelete() {

    }
}
