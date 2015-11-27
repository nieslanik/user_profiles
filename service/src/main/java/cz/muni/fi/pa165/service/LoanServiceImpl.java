package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.LoanDao;
import cz.muni.fi.pa165.entity.Loan;
import cz.muni.fi.pa165.enums.BookState;
import cz.muni.fi.pa165.exceptions.LibraryServiceException;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

import org.springframework.stereotype.Service;

/**
 *
 * @author Jakub Peschel
 */
@Service
public class LoanServiceImpl implements LoanService {

    @Inject
    private LoanDao loanDao;
    
    @Inject
    private BookService bookService;
    
    @Override
    public void create(Loan loan){
        if(loan.getMember() == null)
            throw new LibraryServiceException("Member cannot be null!");      
        if(loan.getBook() == null)
            throw new LibraryServiceException("Book cannot be null!");
        if(loan.getBook().getState().equals(BookState.REMOVED))
            throw new LibraryServiceException("Book cannot be in state REMOVED!");
        loan.setLoanDate(new Date());
        loanDao.create(loan);
    }

    @Override
    public void delete(Loan loan) {
        loanDao.delete(loan);
    }

    @Override
    public List<Loan> findAll() {
        return loanDao.findAll();
    }

    @Override
    public Loan findById(Long id) {
        return loanDao.findById(id);
    }
    
    @Override
    public void returnLoan(Long loanId, BookState returnState) {
        Loan returnedLoan = findById(loanId);
        returnedLoan.setReturned(Boolean.TRUE);
        returnedLoan.setReturnDate(new Date());
        returnedLoan.setReturnBookState(returnState);
        bookService.setState(
                returnedLoan.getBook(),
                returnState
        );
        loanDao.update(returnedLoan);
    }


}
