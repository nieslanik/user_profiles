/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.facade;

import cz.muni.fi.pa036.dto.RestaurantDTO;
import cz.muni.fi.pa036.dto.ReviewDTO;
import cz.muni.fi.pa036.nosql.entities.Restaurant;
import cz.muni.fi.pa036.nosql.entities.Review;
import java.util.List;

/**
 *
 * @author xnieslan
 */
public interface RestaurantFacade {
    
 
/**
* This method is get list of top restaurant.
*     
* @return List<Restaurant> Restaurant's description, name and score
*/
 List<RestaurantDTO> topRestaurants();
 
 
 /**
* This method is used to get average score of one restaurant.
*    
* @param name Name of restaurant
* @return Int This returns average score.
*/
 int averageScore(String name);
 
 /**
* This method is used to add new review
* @param description 
* @param score
* @return Boolean This returns success of adding new review.
*/
 boolean addReview (String description, int score, int restaurantId, int accountId);
 
 /**
* This method is used to remove new review
* @param id
* @return Boolean This returns success of removing review.
*/
 boolean removeReview (int restaurantId, int reviewid);
 
 /**
* This method is used to get list of reviews to one restaurant.
* @param name
* @return list<Review> This returns list of reviews.
*/
 List<ReviewDTO> getReviews(String name);
}
