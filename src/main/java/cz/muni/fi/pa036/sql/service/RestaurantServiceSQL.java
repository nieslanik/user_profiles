/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.sql.service;

import cz.muni.fi.pa036.sql.entities.Restaurant;
import cz.muni.fi.pa036.sql.entities.Review;
import java.util.List;

/**
 *
 * @author xnieslan
 */
public interface RestaurantServiceSQL {
       Restaurant create(Restaurant r) ;
void remove(int restaurantId) ;
 void update(Restaurant r) ;
 void addReview(int restaurantId, Review r, Long userId) ;
void removeReview(int restaurantId, int reviewId) ;
 double getRating(int restaurantId) ;
 Restaurant findById(int id) ;
 Restaurant findByName(String name) ;
 List<Restaurant> findAll() ;
 List<Restaurant> getTopRestaurants();
}
