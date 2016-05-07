/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.nosql.sampledata;

import cz.muni.fi.pa036.nosql.entities.Account;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mato
 */
public class SamplesCreatorTest {
    
    private SamplesCreator creator;
    public SamplesCreatorTest() 
    {
        creator = new SamplesCreator();
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
     * Test of createAccounts method, of class SamplesCreator.
     */
   /* @Test
    public void testCreateAccounts() {
        System.out.println("createAccounts");
        List<Account> accounts = creator.createAccounts(1000);
        for(Account a : accounts)
        {
            System.out.println("ID: " + a.getId() + ", username: " + a.getUsername());
        }
    }*/
    
}
