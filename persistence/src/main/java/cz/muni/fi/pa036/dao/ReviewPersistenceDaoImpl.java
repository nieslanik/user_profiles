/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.dao;
import cz.muni.fi.pa036.entity.Review;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;


/**
 *
 * @author Mojmir
 */
@Repository
public class ReviewPersistenceDaoImpl implements ReviewPersistenceDao{
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void create(Review f) {
        em.persist(f);
    }

    @Override
    public void delete(Review f) {
        em.remove(f);
    }

    @Override
    public void update(Review f) {
        em.merge(f);
    }
    
    @Override
    public List<Review> findAll() {
        return em.createQuery("select f from Review f", Review.class).getResultList();
    }

    @Override
    public Review findByName(String name) {
        List<Review> result = em.createQuery("from Review where name = :name", Review.class)
                .setParameter("name", name).getResultList();
        if (result.isEmpty())
            return null;
        else
            return result.get(0);
    }

    @Override
    public Review findById(Long id) {
        return em.find(Review.class, id);
    }
    
    public List<Review> findByRestaurantId(Long id){
        List<Review> result = em.createQuery("from Review where restaurant_id = :id", Review.class)
                .setParameter("id", id).getResultList();
            return result;
    }
}
