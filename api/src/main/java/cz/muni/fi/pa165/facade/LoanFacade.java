package cz.muni.fi.pa165.facade;

import java.util.List;

import cz.muni.fi.pa165.dto.CreateLoanDTO;
import cz.muni.fi.pa165.dto.LoanDTO;
import cz.muni.fi.pa165.enums.BookState;

/**
 *Facade layer for Loan
 * 
 * @author Jakub Peschel <jakub.peschel@studentagency.cz>
 */
public interface LoanFacade {
    /**
     * Persists loan into database
     *
     * @param loan
     */
    void createLoan(CreateLoanDTO loan);

    /**
     * Returns loan with given id or null
     *
     * @param id oan id
     * @return loan or null
     */
    LoanDTO findById(Long id);

    /**
     * Obtains all persisted loans
     *
     * @return list of all loans
     */
    List<LoanDTO> findAll();

    /**
     * Removes loan from the database
     *
     * @param loanId
     */
    void delete(Long loanId);

    
    /**
     * Return selected loan
     * 
     * @param id id of loan
     * @param returnState State of book when returned
     */
    void returnLoan(Long id, BookState returnState);
}
