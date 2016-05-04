/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa036.sprava_uziv_profilov.nosql.facade;

import java.util.List;
import pa036.sprava_uziv_profilov.nosql.entities.Restaurant;
import pa036.sprava_uziv_profilov.nosql.entities.Review;

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
 List<Restaurant> topRestaurants();
 
 
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
 boolean addReview (String description, int score);
 
 /**
* This method is used to remove new review
* @param id
* @return Boolean This returns success of removing review.
*/
 boolean removeReview (int id);
 
 /**
* This method is used to get list of reviews to one restaurant.
* @param name
* @return list<Review> This returns list of reviews.
*/
 List<Review> getReviews(String name);
}
