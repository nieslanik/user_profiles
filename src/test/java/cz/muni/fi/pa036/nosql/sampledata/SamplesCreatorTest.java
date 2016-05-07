/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.nosql.sampledata;

import cz.muni.fi.pa036.nosql.entities.Account;
import cz.muni.fi.pa036.nosql.entities.Restaurant;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mato
 */
public class SamplesCreatorTest {
    
    private SamplesCreator creator;
    public SamplesCreatorTest() 
    {
        creator = new SamplesCreator();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
        creator.getRestaurantPersistence().RemoveAll();
        creator.getUserPersistence().RemoveAll();
    }

    /**
     * Test of createAccounts method, of class SamplesCreator.
     */
    @Test
    public void testCreateAccounts() 
    {
        System.out.println("createAccounts");
        List<Account> accounts = creator.createAccounts(1000);
        for(Account a : accounts)
        {
            System.out.println("ID: " + a.getId() + ", username: " + a.getUsername());
        }
    }
    
    @Test
    public void testCreateRestaurants() 
    {
        System.out.println("createRestaurants");
        List<Restaurant> restaurants = creator.createRestaurants(1000);
        for(Restaurant r : restaurants)
        {
            System.out.println("ID: " + r.getId() + ", username: " + r.getName());
        }
    }
    
    @Test
    public void testCreateReviewsForRestaurant()
    {
        System.out.println("createReviewsForRestaurant");
        List<Account> users = creator.createAccounts(1000);
        List<Restaurant> restaurants = creator.createRestaurants(100);
        for(Restaurant r : restaurants)
        {
            creator.createReviewsForRestaurant(r.getId(), users, 100);
        }
        
    }
}
