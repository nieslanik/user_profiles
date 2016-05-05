/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.dao;

import cz.muni.fi.pa036.entity.Restaurant;
import java.util.List;


/**
 *
 * @author Mojmir
 */
public interface RestaurantPersistenceDao {
    Restaurant create(Restaurant r);
    //return Restaurant
    
    void delete(Restaurant r);

    
    void update(Restaurant r);
    
    
    List<Restaurant> findAll();

    
    Restaurant findByName(String name);

    
    Restaurant findById(Long id);
    
    double getRating(String restaurantId);
    
    
}
