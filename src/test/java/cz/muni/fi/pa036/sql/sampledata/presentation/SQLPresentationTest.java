/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.sql.sampledata.presentation;
import cz.muni.fi.pa036.sql.sampledata.SamplesCreatorSQL;
import cz.muni.fi.pa036.sql.dao.AccountPersistenceDao;
import cz.muni.fi.pa036.sql.dao.AccountPersistenceDaoImpl;
import cz.muni.fi.pa036.sql.dao.RestaurantPersistenceDao;
import cz.muni.fi.pa036.sql.dao.ReviewPersistenceDao;
import cz.muni.fi.pa036.sql.entities.Account;
import cz.muni.fi.pa036.sql.spring.SpringContext;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SQLPresentationTest {
    
    
    
    @PersistenceContext
    private EntityManager em;
    @Inject
    private SamplesCreatorSQL creator;
    /*private List<Account> accounts;
    private List<Restaurant> restaurants;
    private List<Review> reviews;*/
            
    @Autowired
    private RestaurantPersistenceDao restaurantPersistence;
    @Autowired
    private AccountPersistenceDao userPersistence;

    public void setRestaurantPersistence(RestaurantPersistenceDao restaurantPersistence) {
        this.restaurantPersistence = restaurantPersistence;
    }

    public void setUserPersistence(AccountPersistenceDao userPersistence) {
        this.userPersistence = userPersistence;
    }
    @Autowired
    private ReviewPersistenceDao reviewPersistence;
    
    public ReviewPersistenceDao getReviewPersistence() {
        return reviewPersistence;
    }

    public void setReviewPersistence(ReviewPersistenceDao reviewPersistence) {
        this.reviewPersistence = reviewPersistence;
    }

    
     public AccountPersistenceDao getAccountPersistenceDao() {
        return userPersistence;
    }

    public void setAccountPersistenceDao(AccountPersistenceDao accountPersistenceDao) {
        this.userPersistence = accountPersistenceDao;
    }
    
    public RestaurantPersistenceDao getRestaurantPersistence()
    {
        return restaurantPersistence;
    }
    
    public AccountPersistenceDao getUserPersistence()
    {
        return userPersistence;
    }
    public SQLPresentationTest()
    {
        creator = new SamplesCreatorSQL();
    }
    
    @BeforeClass
    public static void setUpClass() 
    {
        
    }
    
    @org.junit.Test
    @Transactional
    public void statusChangeSQL()
    {
        System.out.println("StatusChangeSQL");
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
            i++;
        }
        Date end = new Date();
        
        System.out.println("StatusChange: " + (end.getTime() - start.getTime()) + " ms.");
        
    }
    @org.junit.Test
    public void addUsers()
    {
        System.out.println("AddSQL");
        Date start = new Date();
        List<Account> accounts = creator.createAccounts(1000);
        Date end = new Date();
        System.out.println("AddUsers: " + (end.getTime() - start.getTime()) + " ms.");
    }
    
    @Test
    public void getUsers()
    {
        System.out.println("GetUsers");
        List<Account> accounts  = creator.createAccounts(100000);
        Date start = new Date();
        List<Account> gotAccounts = userPersistence.findAll();
        Date end = new Date();
        System.out.println("GetUsers: " + (end.getTime() - start.getTime()) + "ms.");
    }
}
