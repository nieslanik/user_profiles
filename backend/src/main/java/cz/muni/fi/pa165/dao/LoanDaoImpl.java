package cz.muni.fi.pa165.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.entity.Loan;

/**
 * Data access object for Loan entity
 *
 * @author Michael Simacek
 *
 */
@Transactional
@Repository
public class LoanDaoImpl implements LoanDao {
    @PersistenceUnit
    private EntityManager em;

    @Override
    public void create(Loan loan) {
        em.persist(loan);
    }

    @Override
    public Loan findById(Long id) {
        return em.find(Loan.class, id);
    }

    @Override
    public List<Loan> findAll() {
        return em.createQuery("from Loan", Loan.class).getResultList();
    }

    @Override
    public void delete(Loan loan) {
        em.remove(findById(loan.getId());
    }

    @Override
    public void update(Loan loan) {
        em.merge(loan);
    }

}
