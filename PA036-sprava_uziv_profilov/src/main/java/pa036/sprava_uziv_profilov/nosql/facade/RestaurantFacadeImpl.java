/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa036.sprava_uziv_profilov.nosql.facade;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pa036.sprava_uziv_profilov.nosql.entities.Restaurant;
import pa036.sprava_uziv_profilov.nosql.entities.Review;
import pa036.sprava_uziv_profilov.nosql.service.AccountService;
import pa036.sprava_uziv_profilov.nosql.service.RestaurantService;

/**
 *
 * @author xnieslan
 */
public class RestaurantFacadeImpl implements RestaurantFacade{

    @Autowired
    private AccountService accountService;
    
    @Autowired
    private RestaurantService restaurantService;


    public AccountService getAccountService() {
        return accountService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public RestaurantService getRestaurantService() {
        return restaurantService;
    }

    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public List<Restaurant> topRestaurants() {

        return restaurantService.getTopRestaurants();
    }
    
    @Override
    public int averageScore(String name) {

        return (int) restaurantService.getRating(name);
    }

    @Override
    public List<Review> getReviews(String name) {
        Restaurant r = restaurantService.findByName(name);
        return r.getReviews();
    }

    @Override
    public boolean addReview(String description, int score, int restaurantId, int accountId) {
        if (description != null && score != 0 && restaurantId != 0 && accountId != 0) {

            String restaurantStringId = Integer.toString(restaurantId);
            String accountStringId = Integer.toString(accountId);

            Review review = new Review();
            review.setRating(score);
            review.setRestaurant_id(restaurantId);
            review.setText(description);
            review.setUserId(accountStringId);

            restaurantService.addReview(restaurantStringId, review, accountStringId);
            return true;
        }
        return false;
    }
    @Override
    public boolean removeReview(int restaurantId, int reviewid) {
        if (restaurantId != 0 && reviewid != 0) {
            String restaurantStringId = Integer.toString(restaurantId);
            String reviewStringId = Integer.toString(reviewid);

            restaurantService.removeReview(restaurantStringId, reviewStringId);
            return true;
        }
        return  false;
    }
}