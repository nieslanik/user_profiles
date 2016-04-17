/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa036.profiles.persistance;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import pa036.profiles.entity.Account;

/**
 *
 * @author Mojmir
 */
@Repository
public class AccountPersistence {
    @PersistenceContext
    private EntityManager em;
    
   
    public void create(Account u) {
        em.persist(u);
    }

    
    public void delete(Account u) {
        em.remove(u);
    }

    
    public void update(Account u) {
        em.merge(u);
    }
    
    
    public List<Account> findAll() {
        return em.createQuery("select u from Account u", Account.class).getResultList();
    }

    
    public Account findByName(String name) {
        List<Account> result = em.createQuery("from Account where name = :name", Account.class)
                .setParameter("name", name).getResultList();
        if (result.isEmpty())
            return null;
        else
            return result.get(0);
    }

    
    public Account findById(Long id) {
        return em.find(Account.class, id);
    }
}


