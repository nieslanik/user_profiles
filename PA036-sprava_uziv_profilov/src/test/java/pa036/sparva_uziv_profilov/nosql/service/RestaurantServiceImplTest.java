/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa036.sparva_uziv_profilov.nosql.service;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import pa036.sprava_uziv_profilov.nosql.entities.Restaurant;
import pa036.sprava_uziv_profilov.nosql.entities.Review;
import pa036.sprava_uziv_profilov.nosql.facade.RestaurantFacade;
import pa036.sprava_uziv_profilov.nosql.persistence.RestaurantPersistence;
import pa036.sprava_uziv_profilov.nosql.service.RestaurantService;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xnieslan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/ApplicationContext.xml")
public class RestaurantServiceImplTest {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantPersistence restaurantPersistence;

    @Autowired
    private RestaurantFacade restaurantFacade;

    public RestaurantFacade getRestaurantFacade() {
        return restaurantFacade;
    }

    public void setRestaurantFacade(RestaurantFacade restaurantFacade) {
        this.restaurantFacade = restaurantFacade;
    }

    public RestaurantPersistence getRestaurantPersistence() {
        return restaurantPersistence;
    }

    public void setRestaurantPersistence(RestaurantPersistence restaurantPersistence) {
        this.restaurantPersistence = restaurantPersistence;
    }

    public RestaurantService getRestaurantService() {
        return restaurantService;
    }

    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    /*
    public RestaurantServiceImplTest()
    {
        rp = new RestaurantPersistence();
        rs = new RestaurantServiceImpl();
        rf = new RestaurantFacadeImpl();
    }
    */


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
        restaurantService.RemoveAll();
    }


    @Test
    public void testCreate() 
    {
        System.out.println("create");
        Restaurant r = new Restaurant();
        r.setName("Restauracia");
        List<Review> reviewList = new ArrayList<>();
        Review review = new Review();
        review.setRating(5);
        reviewList.add(review);
        r.setReviews(reviewList);

        Restaurant savedR = restaurantService.create(r);
        /*assertTrue(restaurantService.findAll().size() == 1);
        
        savedR.getId();
*/
        //restaurantService.create(r);


        List<Review> result = restaurantFacade.getReviews("Restauracia");

        assertTrue(result.size() == 1);

        /*
        
        
        rf.addReview("dfs", 6, savedR.getId() , 8);
        
        assertTrue(rf.getReviews(savedR.getId()).size() == 1);
*/
        
    }

}
