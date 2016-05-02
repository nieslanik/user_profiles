/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa036.sprava_uziv_profilov.nosql.persistence;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pa036.sprava_uziv_profilov.nosql.entities.Account;
import pa036.sprava_uziv_profilov.nosql.entities.Restaurant;
import pa036.sprava_uziv_profilov.nosql.entities.Review;

/**
 *
 * @author Mato
 */
public class RestaurantPersistenceTest {
    
    private RestaurantPersistence rp;

    public RestaurantPersistenceTest() 
    {
        rp = new RestaurantPersistence();
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
    public void tearDown() 
    {
        rp.RemoveAll();
    }

    /**
     * Test of create method, of class RestaurantPersistence.
     */
    @Test
    public void testCreate() 
    {
        System.out.println("create");
        Restaurant r = new Restaurant();
        r.setName("Restauracia");
        Restaurant savedR = rp.create(r);
    }
    
    @Test
    public void testAddReview() 
    {
        System.out.println("addReview");
        Restaurant r = new Restaurant();
        r.setName("Restauracia");
        Restaurant savedR = rp.create(r);
        Review re = new Review();
        re.setText("Blablabla");
        rp.addReview(savedR.getId(), re, "Here is the user's id.");
        System.out.println(rp.findById(savedR.getId()).getReviews().get(0).getId());
        System.out.println(re.getUserId());
    }
    
    @Test
    public void testRemoveReview() 
    {
        System.out.println("removeReview");
        Restaurant r = new Restaurant();
        r.setName("Restauracia");
        Restaurant savedR = rp.create(r);
        Review re = new Review();
        re.setText("Blablabla");
        Review re2 = new Review();
        re2.setText("Ça marche !");
        rp.addReview(savedR.getId(), re, "Here is the id of user");
        rp.addReview(savedR.getId(), re2, "Here is the id of user");
        System.out.println(rp.findById(savedR.getId()).getReviews().size());
        
        
        rp.removeReview(savedR.getId(), re.getId());
        
        System.out.println(rp.findById(savedR.getId()).getReviews().size());
        System.out.println("The text: " + rp.findById(savedR.getId()).getReviews().get(0).getText());
    }
    
    @Test
    public void testUpdate()
    {
        System.out.println("update");
        Restaurant r = new Restaurant();
        r.setName("Restaurant");
        Restaurant savedR = rp.create(r);
        savedR.setName("Changed");
        rp.update(savedR);
        Assert.assertEquals(savedR.getName(), rp.findById(savedR.getId()).getName());
    }
    
    @Test
    public void testRemove()
    {
        System.out.println("remove");
        Restaurant r = new Restaurant();
        r.setName("Restauracia");
        Restaurant savedR = rp.create(r);
        rp.remove(savedR.getId());
    }
    @Test
    public void testGetRating()
    {
        System.out.println("getRating");
        Restaurant r = new Restaurant();
        r.setName("Restauracia");
        Restaurant savedR = rp.create(r);
        Review re = new Review();
        re.setText("Blablabla");
        re.setRating(2);
        Review re2 = new Review();
        re2.setText("Ça marche !");
        re2.setRating(1);
        rp.addReview(savedR.getId(), re, "Here is the id of user");
        rp.addReview(savedR.getId(), re2, "Here is the id of user");
        double rating = rp.getRating(savedR.getId());
        Assert.assertEquals(1.5, rating,0.001);
    }
    
    @Test
     public void testFindById() 
     {
        System.out.println("findById");
        Restaurant r = new Restaurant();
        r.setName("Quelque chose");
        Restaurant savedR = rp.create(r);
        Restaurant gotR = rp.findById(savedR.getId());
        Assert.assertEquals(savedR.getId(), gotR.getId());
        Assert.assertEquals(savedR.getName(), gotR.getName());
    }
    
    @Test
    public void testFindAll() 
    {
        System.out.println("find all");
        Restaurant r = new Restaurant();
        r.setName("fsddsf");
        Restaurant r2 = new Restaurant();
        r2.setName("dfhdf");
        rp.create(r);
        rp.create(r2);
        List<Restaurant> restaurants = rp.findAll();
        System.out.println(restaurants);
        Assert.assertEquals(restaurants.size(), 2);
    }
    
}
