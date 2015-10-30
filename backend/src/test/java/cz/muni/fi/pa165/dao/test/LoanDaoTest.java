package cz.muni.fi.pa165.dao.test;

import cz.muni.fi.pa165.dao.BookDao;
import cz.muni.fi.pa165.dao.LoanDao;
import cz.muni.fi.pa165.entity.Book;
import cz.muni.fi.pa165.entity.BookState;
import cz.muni.fi.pa165.entity.Loan;
import cz.muni.fi.pa165.spring.LibrarySpringContext;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.PersistenceUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Juraj on 10/30/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = LibrarySpringContext.class)
@Transactional
public class LoanDaoTest {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private LoanDao loanDao;

    private Book firstBook = new Book();
    private Book secondBook = new Book();
    private Loan firstLoan = new Loan();
    private Loan secondLoan = new Loan();
    private Loan thirdLoan = new Loan();

    private void createTestData() {
        firstBook.setName("Twilight");
        firstBook.setIsbn(1L);
        firstBook.setState(BookState.NEW);

        secondBook.setName("Fifty shades of gray");
        secondBook.setIsbn(1L);
        secondBook.setState(BookState.LIGHT_DAMAGE);

        em.persist(firstBook);
        em.persist(secondBook);
        em.flush();

        Calendar calendar = new GregorianCalendar(2015, 10, 27);
        Date date = calendar.getTime();
        firstLoan.setDate(date);
        firstLoan.setBook(firstBook);
        firstLoan.setReturnBookState(BookState.NEW);
        firstLoan.setReturnDate(date);

        secondLoan.setDate(date);
        secondLoan.setBook(secondBook);
        secondLoan.setReturnBookState(BookState.LIGHT_DAMAGE);
        secondLoan.setReturnDate(date);

        thirdLoan.setDate(date);
        thirdLoan.setBook(firstBook);
        thirdLoan.setReturnBookState(BookState.HEAVY_DAMAGE);
        thirdLoan.setReturnDate(date);

        loanDao.create(firstLoan);
        loanDao.create(secondLoan);
    }

    @Test
    private void testCreate() {
        createTestData();
        assertNotNull(firstLoan.getId());
        assertNotNull(secondLoan.getId());
        assertNotNull(thirdLoan.getId());
    }

    @Test(expected = PersistenceException.class)
    private void testCreateNullDate() {
        Loan loan = new Loan();
        Calendar calendar = new GregorianCalendar(2015, 1, 1);
        Date date = calendar.getTime();
        loan.setReturnDate(date);
        loan.setReturnBookState(BookState.NEW);
        loanDao.create(loan);
    }

    @Test
    private void testUpdate() {
        createTestData();

        Calendar calendar = new GregorianCalendar(2015, 12, 24);
        Date newDate = calendar.getTime();

        firstLoan.setDate(newDate);
        loanDao.update(firstLoan);

        Loan found = em.find(Loan.class, firstLoan.getId());
        assertEquals(found.getDate(), firstLoan.getDate());
    }

    @Test
    private void testDelete() {
        createTestData();
        loanDao.delete(firstLoan);
        Loan found = em.find(Loan.class, firstLoan.getId());
        assertNull(found);
    }

    @Test
    private void testFindById() {
        createTestData();
        Loan found = loanDao.findById(secondLoan.getId());
        assertNotNull(found);
        assertSame(found, secondLoan);
    }

    @Test
    private void testFindAll() {
        createTestData();
        List<Loan> loans = loanDao.findAll();
        assertEquals(loans.size(), 3);
    }

    @Test(expected = IllegalArgumentException.class)
    private void testDeleteNonExistent() {
        Loan loan = new Loan();
        Calendar calendar = new GregorianCalendar(2015,1,1);
        Date date = calendar.getTime();
        loan.setDate(date);
        loan.setReturnBookState(BookState.HEAVY_DAMAGE);
        loan.setReturnDate(date);
        loan.setBook(firstBook);
        loanDao.delete(loan);
    }
}
