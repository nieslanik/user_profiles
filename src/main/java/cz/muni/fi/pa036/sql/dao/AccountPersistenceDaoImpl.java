/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.sql.dao;

import cz.muni.fi.pa036.sql.entities.Account;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;


/**
 *
 * @author Mojmir
 */
@Repository
public class AccountPersistenceDaoImpl implements AccountPersistenceDao{
    
    @PersistenceContext
    private EntityManager em;
    
   
    @Override
    public Account create(Account u) {
        em.persist(u);
        return u;
    }

    @Override
    public void delete(Account u) {
        em.remove(u);
    }

    @Override
    public void update(Account u) {
        em.merge(u);
    }
    
    @Override
    public List<Account> findAll() {
        return em.createQuery("select u from Account u", Account.class).getResultList();
    }

    @Override
    public Account findByName(String name) {
        List<Account> result = em.createQuery("from Account where username = :name", Account.class)
                .setParameter("name", name).getResultList();
        if (result.isEmpty())
            return null;
        else
            return result.get(0);
    }

    @Override
    public Account findById(int id) {
        return em.find(Account.class, id);
    }
}


