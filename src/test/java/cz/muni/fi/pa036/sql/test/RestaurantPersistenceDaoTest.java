package cz.muni.fi.pa036.sql.test;


 
import cz.muni.fi.pa036.sql.entities.Review;
import static org.junit.Assert.*;
import org.junit.Test;
 
import cz.muni.fi.pa036.sql.dao.RestaurantPersistenceDao;
import cz.muni.fi.pa036.sql.dao.ReviewPersistenceDao;
import cz.muni.fi.pa036.sql.entities.Restaurant;
import cz.muni.fi.pa036.sql.spring.SpringContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringContext.class)
@Transactional
 public class RestaurantPersistenceDaoTest {
     @PersistenceContext
     private EntityManager em;
     
     
     @Inject
     private RestaurantPersistenceDao rp;
     @Inject
     private ReviewPersistenceDao reviewP;
     
     private Restaurant rest;
     @Before
      public void prepareData(){
         rest= new Restaurant();
         rest.setName("Hotel California");
         rp.create(rest);
      }
     
     @Test
     @Transactional
     public void testCreate() {
         Restaurant rest1 = new Restaurant();
         rest1.setName("Burger King");
         rp.create(rest1);
         assertNotNull(rest1.getId());
     }
 
     @Test
     @Transactional
     public void testUpdate() {
         rest.setName("changed");
         rp.update(rest);
         assertEquals("changed", rp.findById(rest.getId()).getName());
     }
 
     @Test
     @Transactional
     public void testTop10()
     {
         int restId = rest.getId();
         Review r = new Review();
         rest.setName("Restauracia1");
         r.setRating(3);
         Restaurant r2 = new Restaurant();
         Review re2 = new Review();
         re2.setRestaurant_id(restId);
         re2.setRating(2);
         
         r2.setName("Restauracia2");
         r2 = rp.create(r2);
         Review e = new Review();
         e.setRating(0);
         e.setRestaurant_id(r2.getId());
         
         
         reviewP.create(re2);
         
         r.setRestaurant_id(restId);
         reviewP.create(r);
         System.out.println("Rating: " + rp.getRating(restId) + " id "+  r.getId() + " rating "+ r.getRating());
         List<Restaurant> top = rp.getTop10();
         for(Restaurant restaurant : top)
         {
             System.out.println(restaurant.getName() + " razeni");
         }
         //assertEquals(top.get(0), rest);
         
     }
     
     @Test
     public void testFindById() {
         assertSame(rest, rp.findById(rest.getId()));
         assertNull(rp.findById(999));
     }
 
     @Test
     public void testFindByName(){
         assertEquals(rest, rp.findByName(rest.getName()));
         assertNull(rp.findByName("nonexistent"));
     }
 
     /*
     @Test
     public void testFindAll() {
         Set<Restaurant> expected = new HashSet<>();
         expected.add(rest);
         Set<Restaurant> actual = new HashSet<>(rp.findAll());
         assertEquals(expected, actual);
     }
     */
 
     @Test
     public void testDelete() {
         rp.delete(rest);
         assertNull(rp.findById(rest.getId()));
     }
 
     @Test(expected = DataAccessException.class)
     public void testDeleteNonexistent() {
         Restaurant newAcc = new Restaurant();
         newAcc.setId(100);
         newAcc.setName("evil");
         rp.delete(newAcc);
     }
 }
