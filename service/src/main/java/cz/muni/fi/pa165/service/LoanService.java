package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.Loan;
import cz.muni.fi.pa165.enums.BookState;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Service layer for loan entity
 * 
 * @author Jakub Peschel <jakub.peschel@studentagency.cz>
 */
@Service
public interface LoanService {
     /**
     * Method creates loan in database.
     *
     * @param loan Loan object to be persisted
     * */
    void create(Loan loan);
    /**
     * Method remove selected loan from database
     * 
     * @param loan Loan to be deleted
     */
    void delete(Loan loan);
    /**
     * Method return collection of all loans
     * 
     * @return Collection of all Loans
     */
    List<Loan> findAll();
    /**
     * Method find loan by id 
     * 
     * @param id Id of loan to be find
     * @return Loan specified by id 
     */
    Loan findById(Long id);
    
    
    /**
     * Method return loan
     * 
     * @param loanId Id of loan to be updated
     * @param returnState state of book when returned
     * @return updated loan
     */
    Loan returnLoan(Long loanId, BookState returnState);
}
