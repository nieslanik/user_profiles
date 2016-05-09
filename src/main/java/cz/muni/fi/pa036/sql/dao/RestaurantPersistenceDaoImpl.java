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
        //  SELECT  AVG(rating) from review JOIN restaurant ON review.restaurant_id = restaurant.id WHERE restaurant.id LIKE 'a';
        
        return em.createQuery("SELECT  AVG(rating) from review JOIN restaurant ON review.restaurant_id = restaurant.id WHERE restaurant.id = :restaurantId", Restaurant.class).setParameter("restaurantId", restaurantId).getFirstResult();
    }
    
    
     @Override
     public List<Restaurant> getTop10()
    {
    // top to review dle rating SELECT restaurant_id from review GROUP BY restaurant_id ORDER BY AVG(rating) LIMIT 10
        //napojení na restaurace  SELECT id, name from restaurant JOIN (SELECT restaurant_id from review GROUP BY restaurant_id ORDER BY AVG(rating) LIMIT 10) a ON a.restaurant_id = restaurant.id;
    
    return em.createQuery("SELECT id, name from restaurant JOIN (SELECT restaurant_id from review GROUP BY restaurant_id ORDER BY AVG(rating) LIMIT 10) a ON a.restaurant_id = restaurant.id", Restaurant.class).getResultList();
   
    }
}
