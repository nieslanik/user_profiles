package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Loan;

/**
 * Data manipulation operations for Loan entity.
 *
 * @author Michael Simacek
 *
 */
public interface LoanDao {
    /**
     * Creates persistent representation of Loan in the database. Sets the ID.
     *
     * @param loan Loan object to be persisted
     * */
    void create(Loan loan);

    /**
     * Finds a loan in the database by its ID
     *
     * @return Found Loan object or null if no loan was found.
     * */
    Loan findById(Long id);

}
