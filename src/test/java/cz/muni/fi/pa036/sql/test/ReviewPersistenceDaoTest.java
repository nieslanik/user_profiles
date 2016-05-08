package cz.muni.fi.pa036.sql.test;


 
import static org.junit.Assert.*;
import org.junit.Test;
 
import cz.muni.fi.pa036.sql.dao.ReviewPersistenceDao;
import cz.muni.fi.pa036.sql.entities.Review;
import cz.muni.fi.pa036.sql.spring.SpringContext;
import java.util.HashSet;
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
 public class ReviewPersistenceDaoTest {
     @PersistenceContext
     private EntityManager em;
     
     
     @Inject
     private ReviewPersistenceDao rep;
     
     private Review rev;
     @Before
      public void prepareData(){
         rev= new Review();
         rev.setRating(5);
         rev.setRestaurant_id(5);
         rev.setText("Such a lovely place. Mirrors on the ceiling, The pink champagne on ice");
         rev.setUser_id(5);
         rep.create(rev);
      }
     
     @Test
     @Transactional
     public void testCreate() {
         Review rev = new Review();
         rev.setRating(6);
         rev.setRestaurant_id(6);
         rev.setText("Best burger ever");
         rev.setUser_id(5);
         rep.create(rev);
         assertNotNull(rev.getId());
     }
 
     @Test
     @Transactional
     public void testUpdate() {
         rev.setText("changed");
         rep.update(rev);
         assertEquals("changed", rep.findById(rev.getId()).getText());
     }
 
     @Test
     public void testFindById() {
         assertSame(rev, rep.findById(rev.getId()));
         assertNull(rep.findById(999));
     }
 
     @Test
     public void testFindByRestaurantId(){
         Set<Review> expected = new HashSet<>();
         expected.add(rev);
         Set<Review> actual = new HashSet<>(rep.findByRestaurantId(rev.getRestaurant_id()));
         assertEquals(expected, actual);
     }
     
     @Test
     public void testFindByAccountId(){
         Set<Review> expected = new HashSet<>();
         expected.add(rev);
         Set<Review> actual = new HashSet<>(rep.findByAccountId(rev.getUser_id()));
         assertEquals(expected, actual);
     }
 
     @Test
     public void testFindAll() {
         Set<Review> expected = new HashSet<>();
         expected.add(rev);
         Set<Review> actual = new HashSet<>(rep.findAll());
         assertEquals(expected, actual);
     }
 
     @Test
     public void testDelete() {
         rep.delete(rev);
         assertNull(rep.findById(rev.getId()));
     }
 
     @Test(expected = DataAccessException.class)
     public void testDeleteNonexistent() {
         Review newAcc = new Review();
         newAcc.setId(100);
         newAcc.setText("evil");
         rep.delete(newAcc);
     }
 }
