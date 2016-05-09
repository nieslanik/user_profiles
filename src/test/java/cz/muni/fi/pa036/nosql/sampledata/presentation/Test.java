/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.nosql.sampledata.presentation;
import cz.muni.fi.pa036.nosql.entities.Account;
import cz.muni.fi.pa036.nosql.entities.Restaurant;
import cz.muni.fi.pa036.nosql.entities.Review;
import cz.muni.fi.pa036.nosql.persistence.RestaurantPersistence;
import cz.muni.fi.pa036.nosql.persistence.UserPersistence;
import cz.muni.fi.pa036.nosql.sampledata.SamplesCreator;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.junit.BeforeClass;
/**
 *
 * @author Mato
 */
public class Test {
    
    private SamplesCreator creator;
    private UserPersistence userPersistance;
    private RestaurantPersistence restaurantPersistance;
    
    /*private List<Account> accounts;
    private List<Restaurant> restaurants;
    private List<Review> reviews;*/
            
    public Test()
    {
        creator = new SamplesCreator();
        userPersistance = new UserPersistence();
        restaurantPersistance = new RestaurantPersistence();
    }
    
    @BeforeClass
    public static void setUpClass() 
    {
        
    }
    
    @org.junit.Test
    public void statusChangeNoSQL()
    {
        System.out.println("StatusChangeNoSQL");
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
            Account a = userPersistance.findById(accounts.get(index).getId());
            a.setLogon_status(a.getLogon_status() ^ 1);
            userPersistance.update(a);
            i++;
        }
        Date end = new Date();
        
        System.out.println("StatusChange: " + (end.getTime() - start.getTime()) + " ms.");
        
    }
    
}
