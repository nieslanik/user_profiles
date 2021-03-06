/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa036.sprava_uziv_profilov.nosql.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pa036.sprava_uziv_profilov.nosql.entities.Restaurant;
import pa036.sprava_uziv_profilov.nosql.entities.Review;
import pa036.sprava_uziv_profilov.nosql.persistence.RestaurantPersistence;

/**
 *
 * @author xnieslan
 */
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    private RestaurantPersistence restaurantPersistence;

    @Override
    public Restaurant create(Restaurant r) {
        return restaurantPersistence.create(r);
    }

    @Override
    public void remove(String restaurantId) {
        restaurantPersistence.remove(restaurantId);
    }

    @Override
    public void update(Restaurant r) {
        restaurantPersistence.update(r);
    }

    @Override
    public void addReview(String restaurantId, Review r, String userId) {
        restaurantPersistence.addReview(restaurantId, r, userId);
    }

    @Override
    public void removeReview(String restaurantId, String reviewId) {
        restaurantPersistence.removeReview(restaurantId, reviewId);
    }

    @Override
    public double getRating(String restaurantId) {
        return restaurantPersistence.getRating(restaurantId);
    }

    @Override
    public Restaurant findById(String id) {
        return restaurantPersistence.findById(id);
    }

    @Override
    public Restaurant findByName (String name){
        return restaurantPersistence.findByName(name);
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantPersistence.findAll();
    }

    @Override
    public void RemoveAll() {
       restaurantPersistence.RemoveAll();
    }

    @Override
    public List<Restaurant> getTopRestaurants() {
        return restaurantPersistence.getTop10();
    }

    public void setRestaurantPersistence(RestaurantPersistence restaurantPersistence) {
        this.restaurantPersistence = restaurantPersistence;
    }

    public RestaurantPersistence getRestaurantPersistence() {
        return restaurantPersistence;
    }
}
