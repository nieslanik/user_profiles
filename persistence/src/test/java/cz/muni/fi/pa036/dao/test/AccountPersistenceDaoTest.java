package cz.muni.fi.pa036.dao.test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa036.dao.AccountPersistenceDao;
import cz.muni.fi.pa036.entity.Account;
import cz.muni.fi.pa036.spring.SpringContext;

/**
 * @author Michael Simacek
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringContext.class)
@Transactional
public class AccountPersistenceDaoTest {
    @PersistenceContext
    private EntityManager em;

    @Inject
    private AccountPersistenceDao ap;

    private Account acc;

    public void prepareData() {
        acc = new Account();
        acc.setUsername("Thor son of a Odin");
        acc.setPassword("checkoutmypacks");
        em.persist(acc);
        em.flush();
    }

    @Test
    @Transactional
    public void testCreate() {
        prepareData();
        ap.create(acc);
        assertNotNull(acc.getId());
    }

    /*@Test(expected = DataAccessException.class)
    public void testCreateNullName() {
        Account newAcc = new Account();
        ap.create(newAcc);
    }*/

    @Test
    @Transactional
    public void testUpdate() {
        prepareData();
        acc.setUsername("changed");
        ap.update(acc);
        assertEquals("changed", em.find(Account.class, acc.getId()).getUsername());
    }

    @Test
    public void testFindById() {
        prepareData();
        assertSame(acc, ap.findById(new Long(acc.getId())));
        assertNull(ap.findById(666L));
    }

    @Test
    public void testFindByName() {
        prepareData();
        assertEquals(acc, ap.findByName(acc.getUsername()));
        assertEquals(acc, ap.findByName(acc.getUsername()));
        assertNull(ap.findByName("nonexistent"));
    }

    @Test
    public void testFindAll() {
        prepareData();
        Set<Account> expected = new HashSet<>();
        expected.add(acc);
        Set<Account> actual = new HashSet<>(ap.findAll());
        assertEquals(expected, actual);
    }

    @Test
    public void testDelete() {
        prepareData();
        ap.delete(acc);
        assertNull(em.find(Account.class, acc.getId()));
    }
/*
    @Test(expected = DataAccessException.class)
    public void testDeleteNonexistent() {
        Account newAcc = new Account();
        newAcc.setId(100);
        newAcc.setUsername("evil");
        ap.delete(newAcc);
    }*/
}