/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.sql.dao;
import cz.muni.fi.pa036.sql.entities.Restaurant;
import cz.muni.fi.pa036.sql.entities.Review;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

/**
 *
 * @author Mojmir
 */
@Repository
public class RestaurantPersistenceDaoImpl implements RestaurantPersistenceDao {
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Restaurant create(Restaurant r) {
        em.persist(r);
        return r;
    }

    @Override
    public void delete(Restaurant r) {
        em.remove(r);
    }

    @Override
    public void update(Restaurant r) {
        em.merge(r);
    }
    
    @Override
    public List<Restaurant> findAll() {
        return em.createQuery("select r from Restaurant r", Restaurant.class).getResultList();
    }

    @Override
    public Restaurant findByName(String name) {
        List<Restaurant> result = em.createQuery("from Restaurant where name = :name", Restaurant.class)
                .setParameter("name", name).getResultList();
        if (result.isEmpty())
            return null;
        else
            return result.get(0);
    }

    @Override
    public Restaurant findById(int id) {
        return em.find(Restaurant.class, id);
    }
    
    @Override
    public double getRating(int restaurantId){
        final ReviewPersistenceDao rep= new ReviewPersistenceDaoImpl();
        List<Review>list = rep.findByRestaurantId(restaurantId);
        Long rate=0L;
        for(int i=0;i<list.size();i++){
            rate+=new Long(list.get(i).getRestaurant_id());
        }
        return rate/new  Long(list.size());
    }
}
