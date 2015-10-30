package cz.muni.fi.pa165.dao.test;

import cz.muni.fi.pa165.dao.BookDao;
import cz.muni.fi.pa165.dao.LoanDao;
import cz.muni.fi.pa165.entity.Book;
import cz.muni.fi.pa165.entity.BookState;
import cz.muni.fi.pa165.entity.Loan;
import cz.muni.fi.pa165.spring.LibrarySpringContext;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

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

    @Inject
    private BookDao bookDao;

    private Book firstBook = new Book();
    private Book secondBook = new Book();
    private Loan firstLoan = new Loan();
    private Loan secondLoan = new Loan();

    @BeforeClass
    private void setup() {
        firstBook.setName("Twilight");
        firstBook.setIsbn(1L);
        firstBook.setState(BookState.NEW);
        
    }
}
