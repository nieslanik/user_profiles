/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.nosql.sampledata;

import cz.muni.fi.pa036.nosql.entities.Account;
import cz.muni.fi.pa036.nosql.entities.Restaurant;
import cz.muni.fi.pa036.nosql.entities.Review;
import cz.muni.fi.pa036.nosql.persistence.RestaurantPersistence;
import cz.muni.fi.pa036.nosql.persistence.UserPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mato
 */
@Service
public class SamplesCreator {
    private RestaurantPersistence rp;
    private UserPersistence up;
    
    public SamplesCreator()
    {
        rp = new RestaurantPersistence();
        up = new UserPersistence();
    }
    
    public RestaurantPersistence getRestaurantPersistence()
    {
        return rp;
    }
    
    public UserPersistence getUserPersistence()
    {
        return up;
    }
    
    public List<Account> createAccounts(int n)
    {
        List<Account> accounts = new ArrayList<>();
        for(int i = 0; i < n; i++)
        {
            Account a = new Account();
            Random r = new Random();
            a.setUsername("User"+r.nextInt());
            a.setCompany_key(r.nextInt());
            a.setUi_lang("English");
            a.setAcces_rights("Access rights"+r.nextInt());
            accounts.add(up.create(a));
        }
        return accounts;
    }
    
    public List<Restaurant> createRestaurants(int n)
    {
        List<Restaurant> restaurants = new ArrayList<>();
        for(int i = 0; i < n; i++)
        {
            Restaurant restaurant = new Restaurant();
            Random r = new Random();
            restaurant.setName("Restaurant"+ r.nextInt());
            restaurants.add(rp.create(restaurant));
        }
        return restaurants;
    }
    
    public List<Review> createReviewsForRestaurant(String restaurantId, List<Account> users, int n)
    {
        List<Review> reviews = new ArrayList<>();
        
        for(int i = 0; i < n; i++)
        {
            Review review = new Review();
            Random r = new Random();
            review.setRating(r.nextInt(5) + 1);//assuming we use 5 star rating
            review.setRestaurant_id(restaurantId);
            String userId = users.get(r.nextInt(users.size())).getId();
            review.setUserId(userId);
            review.setText("Text" + r.nextInt());
            reviews.add(rp.addReview(restaurantId, review, userId));
        }
        
        return reviews;
    }
}
