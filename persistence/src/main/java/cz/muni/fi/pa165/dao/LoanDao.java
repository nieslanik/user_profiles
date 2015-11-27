package cz.muni.fi.pa165.dao;

import java.util.List;

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
     * @param loan
     *            Loan object to be persisted
     */
    void create(Loan loan);

    /**
     * Updates entity data in database
     *
     * @param loan
     *            Loan object to be updated
     */
    void update(Loan loan);

    /**
     * Finds a loan in the database by its ID
     *
     * @param id
     *            loan id
     * @return Found Loan object or null if no loan was found.
     */
    Loan findById(Long id);

    /**
     * Retrieves all Loans from the database
     *
     * @return List of all Loans
     */
    List<Loan> findAll();

    /**
     * Removes given Loan from the database
     *
     * @param loan
     *            loan id
     */
    void delete(Loan loan);
}
