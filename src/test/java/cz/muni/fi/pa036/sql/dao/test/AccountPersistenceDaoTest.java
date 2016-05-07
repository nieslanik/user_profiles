package cz.muni.fi.pa036.sql.dao.test;


 
import static org.junit.Assert.*;
import org.junit.Test;
 
import cz.muni.fi.pa036.sql.dao.AccountPersistenceDao;
import cz.muni.fi.pa036.sql.entities.Account;
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
 public class AccountPersistenceDaoTest {
     @PersistenceContext
     private EntityManager em;
     
     
     @Inject
     private AccountPersistenceDao ap;
     
     private Account acc;
     @Before
      public void prepareData(){
         acc= new Account();
         acc.setUsername("Freddie Mercuri");
         acc.setPassword("THESHOWMUSTGOON!");
         ap.create(acc);
      }
     
     @Test
     @Transactional
     public void testCreate() {
         Account acc1 = new Account();
         acc1.setUsername("Alojz Novak");
         acc1.setPassword("THISISPASSWORD!");
         ap.create(acc1);
         assertNotNull(acc1.getId());
     }
 
     @Test
     @Transactional
     public void testUpdate() {
         acc.setUsername("changed");
         ap.update(acc);
         assertEquals("changed", ap.findById(acc.getId()).getUsername());
     }
 
     @Test
     public void testFindById() {
         assertSame(acc, ap.findById(acc.getId()));
         assertNull(ap.findById(999));
     }
 
     @Test
     public void testFindByName(){
         assertEquals(acc, ap.findByName(acc.getUsername()));
         assertNull(ap.findByName("nonexistent"));
     }
 
     @Test
     public void testFindAll() {
         Set<Account> expected = new HashSet<>();
         expected.add(acc);
         Set<Account> actual = new HashSet<>(ap.findAll());
         assertEquals(expected, actual);
     }
 
     @Test
     public void testDelete() {
         ap.delete(acc);
         assertNull(ap.findById(acc.getId()));
     }
 
     @Test(expected = DataAccessException.class)
     public void testDeleteNonexistent() {
         Account newAcc = new Account();
         newAcc.setId(100);
         newAcc.setUsername("evil");
         ap.delete(newAcc);
     }
 }
