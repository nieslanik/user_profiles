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

import pa036.profiles.entity.Review;
/**
 *
 * @author Mojmir
 */
@Repository
public class ReviewPersistence {
    @PersistenceContext
    private EntityManager em;
    
   
    public void create(Review f) {
        em.persist(f);
    }

    
    public void delete(Review f) {
        em.remove(f);
    }

    
    public void update(Review f) {
        em.merge(f);
    }
    
    
    public List<Review> findAll() {
        return em.createQuery("select f from Review f", Review.class).getResultList();
    }

    
    public Review findByName(String name) {
        List<Review> result = em.createQuery("from Review where name = :name", Review.class)
                .setParameter("name", name).getResultList();
        if (result.isEmpty())
            return null;
        else
            return result.get(0);
    }

    
    public Review findById(Long id) {
        return em.find(Review.class, id);
    }
}
