/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.sql.sampledata;

import cz.muni.fi.pa036.sql.dao.AccountPersistenceDao;
import cz.muni.fi.pa036.sql.dao.RestaurantPersistenceDao;
import cz.muni.fi.pa036.sql.entities.Account;
import cz.muni.fi.pa036.sql.entities.Restaurant;
import cz.muni.fi.pa036.sql.entities.Review;
import cz.muni.fi.pa036.sql.spring.SpringContext;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mato
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringContext.class)
@Transactional
public class SamplesCreatorTest {
    @PersistenceContext
    private EntityManager em;
    @Inject
    private SamplesCreatorSQL creator;
    public SamplesCreatorTest() 
    {
        creator = new SamplesCreatorSQL();
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
    }

    /**
     * Test of createAccounts method, of class SamplesCreatorSQL.
     */
    @Test
    public void testCreateAccounts() 
    {
        System.out.println("SQLcreateAccounts");
        List<Account> accounts = creator.createAccounts(1000); 
        for(Account a : accounts)
        {
            System.out.println("ID: " + a.getId() + ", username: " + a.getUsername());
        }
    }
    
    @Test
    public void testCreateRestaurants() 
    {
        System.out.println("SQLcreateRestaurants");
        List<Restaurant> restaurants = creator.createRestaurants(1000);
        for(Restaurant r : restaurants)
        {
            System.out.println("ID: " + r.getId() + ", username: " + r.getName());
        }
    }
    
    @Test
    public void testCreateReviewsForRestaurant()
    {
        System.out.println("SQLcreateReviewsForRestaurant");
        List<Account> users = creator.createAccounts(1000);
        List<Restaurant> restaurants = creator.createRestaurants(100);
        for(Restaurant r : restaurants)
        {
            creator.createReviewsForRestaurant(r.getId(), users, 100);
        }
        
    }
    
}
