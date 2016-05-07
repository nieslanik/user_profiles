import cz.muni.fi.pa036.dto.RestaurantDTO;
import cz.muni.fi.pa036.dto.ReviewDTO;
import cz.muni.fi.pa036.facade.AccountFacade;
import cz.muni.fi.pa036.facade.RestaurantFacade;
import cz.muni.fi.pa036.nosql.entities.Restaurant;
import cz.muni.fi.pa036.nosql.entities.Review;
import cz.muni.fi.pa036.nosql.persistence.RestaurantPersistence;
import cz.muni.fi.pa036.nosql.service.RestaurantService;
import cz.muni.fi.pa036.sql.dao.RestaurantPersistenceDao;
import cz.muni.fi.pa036.sql.service.RestaurantServiceSQL;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by xnieslan on 07.05.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/ApplicationContext.xml")
public class FacadeTest {

    @Autowired
    private RestaurantFacade restaurantFacade;

    @Autowired
    private AccountFacade accountFacade;

    @Autowired RestaurantService restaurantService;

    public RestaurantFacade getRestaurantFacade() {
        return restaurantFacade;
    }

    public void setRestaurantFacade(RestaurantFacade restaurantFacade) {
        this.restaurantFacade = restaurantFacade;
    }

    public AccountFacade getAccountFacade() {
        return accountFacade;
    }

    public void setAccountFacade(AccountFacade accountFacade) {
        this.accountFacade = accountFacade;
    }

    public RestaurantService getRestaurantService() {
        return restaurantService;
    }

    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
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
        restaurantService.RemoveAll();
    }


    @Test
    public void testCreate()
    {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("restaurant");
        Review review = new Review();
        review.setRating(1);
        Restaurant created = restaurantService.create(restaurant);
        restaurantService.addReview(created.getId(),review,"Rest");

        Restaurant restaurant1 = new Restaurant();
        restaurant1.setName("restaurant1");
        Review review1 = new Review();
        review.setRating(2);
        Restaurant created1 = restaurantService.create(restaurant1);
        restaurantService.addReview(created1.getId(),review,"Rest1");

        List<RestaurantDTO> topRest = restaurantFacade.topRestaurants();

        String name = topRest.get(0).getName();

        assertTrue(name.equals("restaurant1"));
    }

    }
