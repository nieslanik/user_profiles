package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.LoanDao;
import cz.muni.fi.pa165.entity.Loan;
import cz.muni.fi.pa165.enums.BookState;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

import org.springframework.stereotype.Service;

/**
 *
 * @author Jakub Peschel <jakub.peschel@studentagency.cz>
 */
@Service
public class LoanServiceImpl implements LoanService {

    @Inject
    LoanDao loanDao;
    
    @Override
    public void create(Loan loan) {
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
    public Loan returnLoan(Long loanId, BookState returnState) {
        Loan returnedLoan = findById(loanId);
        returnedLoan.setReturned(Boolean.TRUE);
        returnedLoan.setReturnDate(new Date());
        returnedLoan.setReturnBookState(returnState);
        loanDao.update(returnedLoan);
        return findById(returnedLoan.getId());
    }


}
