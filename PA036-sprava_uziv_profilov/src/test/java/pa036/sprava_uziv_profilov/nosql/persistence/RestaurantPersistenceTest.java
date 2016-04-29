/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa036.sprava_uziv_profilov.nosql.persistence;

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
    public void tearDown() {
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
        rp.AddReview(savedR.getId(), re);
        System.out.println(rp.findById(savedR.getId()).getReviews().get(0).getId());
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
    
}
