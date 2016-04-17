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

import pa036.profiles.entity.Restaurant;
/**
 *
 * @author Mojmir
 */
@Repository
public class RestaurantPersistence {
    @PersistenceContext
    private EntityManager em;
    
   
    public void create(Restaurant r) {
        em.persist(r);
    }

    
    public void delete(Restaurant r) {
        em.remove(r);
    }

    
    public void update(Restaurant r) {
        em.merge(r);
    }
    
    
    public List<Restaurant> findAll() {
        return em.createQuery("select r from Restaurant r", Restaurant.class).getResultList();
    }

    
    public Restaurant findByName(String name) {
        List<Restaurant> result = em.createQuery("from Restaurant where name = :name", Restaurant.class)
                .setParameter("name", name).getResultList();
        if (result.isEmpty())
            return null;
        else
            return result.get(0);
    }

    
    public Restaurant findById(Long id) {
        return em.find(Restaurant.class, id);
    }
}
