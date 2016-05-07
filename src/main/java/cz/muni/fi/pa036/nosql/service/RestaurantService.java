/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.nosql.service;

import com.mongodb.BasicDBObject;
import cz.muni.fi.pa036.nosql.entities.Restaurant;
import cz.muni.fi.pa036.nosql.entities.Review;
import java.util.List;
import org.bson.types.ObjectId;
import org.mongojack.DBUpdate;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;


/**
 *
 * @author xnieslan
 */
public interface RestaurantService {
    Restaurant create(Restaurant r);

    void remove(String restaurantId);

    void update(Restaurant r);

    void addReview(String restaurantId, Review r, String userId);

    void removeReview(String restaurantId, String reviewId);

    double getRating(String restaurantId);

    Restaurant findById(String id) ;

    Restaurant findByName(String name) ;

    List<Restaurant> findAll() ;

    void RemoveAll();

    List<Restaurant> getTopRestaurants();
    
}
