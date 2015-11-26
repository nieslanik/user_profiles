package cz.muni.fi.pa165.service.test;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cz.muni.fi.pa165.config.ServiceConfiguration;
import cz.muni.fi.pa165.dao.LoanDao;
import cz.muni.fi.pa165.entity.Book;
import cz.muni.fi.pa165.entity.Loan;
import cz.muni.fi.pa165.entity.Member;
import cz.muni.fi.pa165.enums.BookState;
import cz.muni.fi.pa165.exceptions.LibraryServiceException;
import cz.muni.fi.pa165.service.BookService;
import cz.muni.fi.pa165.service.LoanService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfiguration.class)
public class LoanServiceTest {

    @Mock
    LoanDao daoMock;

    @Mock
    BookService bookServiceMock;

    @Inject
    @InjectMocks
    LoanService service;

    private Member member;
    private Book book;
    private Loan loan;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        member = new Member();
        member.setId(1L);
        member.setGivenName("Blanka");
        member.setSurname("Protrhla");
        member.setEmail("omg@gmail.com");
        member.setPasswordHash("password");
        member.setRegistrationDate(new Date(0));
        book = new Book();
        book.setId(1L);
        book.setName("Varime s konopim");
        book.setIsbn(1L);
        book.setState(BookState.MEDIUM_DAMAGE);
        loan = new Loan();
        loan.setId(1L);
        loan.setMember(member);
        loan.setBook(book);
    }

    @Test
    public void testCreate() {
        service.create(loan);
        verify(daoMock).create(loan);
        assertNotNull(loan.getLoanDate());
        assertNull(loan.getReturnBookState());
        assertNull(loan.getReturnDate());
    }

    @Test(expected = LibraryServiceException.class)
    public void testCreateNoMember() {
        loan.setMember(null);
        service.create(loan);
    }

    @Test(expected = LibraryServiceException.class)
    public void testCreateNoBook() {
        loan.setBook(null);
        service.create(loan);
    }

    @Test(expected = LibraryServiceException.class)
    public void testCreateRemovedBook() {
        // bok can be marked as removed to keep loan history, but not removed
        // from db
        book.setState(BookState.REMOVED);
        service.create(loan);
    }

    @Test
    public void testReturnLoan() {
        when(daoMock.findById(loan.getId())).thenReturn(loan);
        service.returnLoan(loan.getId(), BookState.HEAVY_DAMAGE);
        assertNotNull(loan.getReturnDate());
        assertEquals(BookState.HEAVY_DAMAGE, loan.getReturnBookState());
        verify(bookServiceMock).setState(book, BookState.HEAVY_DAMAGE);
        verify(daoMock).update(loan);
    }

    @Test
    public void testFindById() {
        when(daoMock.findById(loan.getId())).thenReturn(loan);
        assertSame(loan, service.findById(loan.getId()));
    }

    @Test
    public void testFindAll() {
        List<Loan> loans = new ArrayList<>();
        loans.add(loan);
        when(daoMock.findAll()).thenReturn(loans);
        assertSame(loans, service.findAll());
    }

    @Test
    public void testDelete() {
        service.delete(loan);
        verify(daoMock).delete(loan);
    }
}
