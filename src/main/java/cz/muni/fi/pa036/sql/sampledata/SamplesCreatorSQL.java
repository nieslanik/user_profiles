/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.sql.sampledata;

import cz.muni.fi.pa036.sql.entities.Account;
import cz.muni.fi.pa036.sql.entities.Restaurant;
import cz.muni.fi.pa036.sql.entities.Review;
import cz.muni.fi.pa036.sql.dao.AccountPersistenceDao;
import cz.muni.fi.pa036.sql.dao.RestaurantPersistenceDao;
import cz.muni.fi.pa036.sql.dao.ReviewPersistenceDao;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mato
 */
@Service
public class SamplesCreatorSQL {
    
    @Autowired
    private RestaurantPersistenceDao restaurantPersistence;
    @Autowired
    private AccountPersistenceDao userPersistence;

    public void setRestaurantPersistence(RestaurantPersistenceDao restaurantPersistence) {
        this.restaurantPersistence = restaurantPersistence;
    }

    public void setUserPersistence(AccountPersistenceDao userPersistence) {
        this.userPersistence = userPersistence;
    }
    @Autowired
    private ReviewPersistenceDao reviewPersistence;
    
    public ReviewPersistenceDao getReviewPersistence() {
        return reviewPersistence;
    }

    public void setReviewPersistence(ReviewPersistenceDao reviewPersistence) {
        this.reviewPersistence = reviewPersistence;
    }

    
    public SamplesCreatorSQL()
    {
        /*restaurantPersistence = new RestaurantPersistenceDaoImpl();
        userPersistence = new AccountPersistenceDaoImpl();
        reviewPersistence = new ReviewPersistenceDaoImpl();*/
    }
    
     public AccountPersistenceDao getAccountPersistenceDao() {
        return userPersistence;
    }

    public void setAccountPersistenceDao(AccountPersistenceDao accountPersistenceDao) {
        this.userPersistence = accountPersistenceDao;
    }
    
    public RestaurantPersistenceDao getRestaurantPersistence()
    {
        return restaurantPersistence;
    }
    
    public AccountPersistenceDao getUserPersistence()
    {
        return userPersistence;
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
            accounts.add(userPersistence.create(a));
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
            restaurants.add(restaurantPersistence.create(restaurant));
        }
        return restaurants;
    }
    
    public List<Review> createReviewsForRestaurant(int restaurantId, List<Account> users, int n)
    {
        List<Review> reviews = new ArrayList<>();
        
        for(int i = 0; i < n; i++)
        {
            Review review = new Review();
            Random r = new Random();
            review.setRating(r.nextInt(5) + 1);//assuming we use 5 star rating
            review.setRestaurant_id(restaurantId);
            int userId = users.get(r.nextInt(users.size())).getId();
            review.setUser_id(userId);
            review.setText("Text" + r.nextInt());
            reviews.add(reviewPersistence.create(review));
        }
        
        return reviews;
    }
}
