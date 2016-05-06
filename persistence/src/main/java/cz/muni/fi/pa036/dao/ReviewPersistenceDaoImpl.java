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
    public Review findById(int id) {

        return em.find(Review.class, id);
    }

    @Override
    public List<Review> findByRestaurantId(int id){
        List<Review> result = em.createQuery("from Review where restaurant_id = :id", Review.class)
                .setParameter("id", id).getResultList();
            return result;
    }

    @Override
    public List<Review> findByAccountId(int id){
        List<Review> result = em.createQuery("from Review where user_id = :id", Review.class)
                .setParameter("id", id).getResultList();
            return result;
    }
}
