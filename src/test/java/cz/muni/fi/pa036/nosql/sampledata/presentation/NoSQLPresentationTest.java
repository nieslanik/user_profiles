/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.nosql.sampledata.presentation;
import cz.muni.fi.pa036.nosql.entities.Account;
import cz.muni.fi.pa036.nosql.entities.Restaurant;
import cz.muni.fi.pa036.nosql.entities.Review;
import cz.muni.fi.pa036.nosql.persistence.RestaurantPersistence;
import cz.muni.fi.pa036.nosql.persistence.UserPersistence;
import cz.muni.fi.pa036.nosql.sampledata.SamplesCreator;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 *
 * @author Mato
 */
public class NoSQLPresentationTest {
    
    private SamplesCreator creator;
    private UserPersistence userPersistence;
    private RestaurantPersistence restaurantPersistence;
    
    /*private List<Account> accounts;
    private List<Restaurant> restaurants;
    private List<Review> reviews;*/
            
    public NoSQLPresentationTest()
    {
        creator = new SamplesCreator();
        userPersistence = new UserPersistence();
        restaurantPersistence = new RestaurantPersistence();
    }
    
    @BeforeClass
    public static void setUpClass() 
    {
        
    }
    
    @Test
    public void statusChangeNoSQL()
    {
        System.out.println("StatusChangeNoSQL");
        List<Account> accounts = creator.createAccounts(1000);
        for(int i = 0; i < accounts.size(); i++)
        {
            accounts.get(i).setLogon_status(1);
        }
        
        for(int i = accounts.size()/2; i < accounts.size(); i++)
        {
            accounts.get(i).setLogon_status(1);
        }
        
        Random r = new Random();

        int i = 0;
        //DateTime start = DateTime.start();
        Date start = new Date();
        
        while(i < accounts.size()/2)
        {
            int index = r.nextInt(accounts.size());
            Account a = userPersistence.findById(accounts.get(index).getId());
            a.setLogon_status(a.getLogon_status() ^ 1);
            userPersistence.update(a);
            //userPersistance.changeLogOnStatus(a, index);
            i++;
        }
        Date end = new Date();
        
        System.out.println("StatusChange: " + (end.getTime() - start.getTime()) + " ms.");
        userPersistence.RemoveAll();
    }
    
    @Test
    public void addUsers()
    {
        System.out.println("AddNoSQL");
        Date start = new Date();
        List<Account> accounts = creator.createAccounts(1000);
        Date end = new Date();
        System.out.println("AddUsers: " + (end.getTime() - start.getTime()) + " ms.");
        userPersistence.RemoveAll();
    }
    @Test
    public void getUsers()
    {
        System.out.println("GetUsers");
        List<Account> accounts  = creator.createAccounts(1000);
        Date start = new Date();
        List<Account> gotAccounts = userPersistence.findAll();
        Date end = new Date();
        System.out.println("GetUsers: " + (end.getTime() - start.getTime()) + "ms.");
        userPersistence.RemoveAll();
    }
    
    @Test
    public void averageRating()
    {
        System.out.println("AverageRating");
        List<Account> users = creator.createAccounts(1000);
        List<Restaurant> restaurants = creator.createRestaurants(1);
        for(Restaurant r : restaurants)
        {
            creator.createReviewsForRestaurant(r.getId(), users, 1000);
        }
        
        Random r = new Random();
        
        
        String restaurantId = restaurants.get(r.nextInt(restaurants.size())).getId();
        Date start = new Date();
        restaurantPersistence.getRating(restaurantId);
        Date end = new Date();
        System.out.println("GetAverage: " + (end.getTime() - start.getTime()) + " ms.");
        restaurantPersistence.RemoveAll();
    }
    
    @Test
    public void getTop10()
    {
        System.out.println("GetTop10");
        
        List<Account> users = creator.createAccounts(1000);
        List<Restaurant> restaurants = creator.createRestaurants(100);
        for(Restaurant r : restaurants)
        {
            creator.createReviewsForRestaurant(r.getId(), users, 100);
        }
        
        Random r = new Random();
        
        Date start = new Date();
        restaurantPersistence.getTop10();
        Date end = new Date();
        
        System.out.println("GetTop10: " + (end.getTime() - start.getTime()) + " ms.");
        restaurantPersistence.RemoveAll();
        
    }
    
}