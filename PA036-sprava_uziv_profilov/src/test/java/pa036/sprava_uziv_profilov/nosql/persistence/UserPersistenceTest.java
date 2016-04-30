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
import org.mongojack.DBCursor;
import pa036.sprava_uziv_profilov.nosql.entities.Account;

/**
 *
 * @author Mato
 */
public class UserPersistenceTest {
    
    private UserPersistence p;
    public UserPersistenceTest() 
    {
        p = new UserPersistence();
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
        p.RemoveAll();
    }

    /**
     * Test of create method, of class UserPersistence.
     */
    @org.junit.Test
    public void testCreate() {
        System.out.println("create");
        Account u = new Account();
        u.setEmployee_acount(true);
        u.setUsername("1stmato");
        u.setPassword("password");
        u.setUi_lang("français");
        Account savedU = p.create(u);
    }
    
    @org.junit.Test
    public void testDelete() {
        System.out.println("delete");
        Account u = new Account();
        u.setEmployee_acount(true);
        u.setUsername("1stmato");
        u.setPassword("password");
        u.setUi_lang("français");
        Account savedU = p.create(u);
        
        p.delete(savedU);
    }
    
    @org.junit.Test
    public void testUpdate() 
    {
        System.out.println("update");
        Account u = new Account();
        u.setEmployee_acount(true);
        u.setUsername("1stmato");
        u.setPassword("password");
        u.setUi_lang("français");
        Account savedU = p.create(u);
        savedU.setUsername("new");
        p.update(savedU);
        Assert.assertEquals(savedU.getUsername(), p.findById(savedU.getId()).getUsername());
    }
    
    @org.junit.Test
    public void testFindByName() 
    {
        System.out.println("find by name");
        Account u = new Account();
        u.setEmployee_acount(true);
        u.setUsername("1stmato");
        u.setPassword("password");
        u.setUi_lang("français");
        p.create(u);
        List<Account> accounts = p.findByName("1stmato");
        System.out.println(accounts);
        Assert.assertEquals(accounts.size(), 1);
    }
    
    @org.junit.Test
    public void testFindAll() 
    {
        System.out.println("find all");
        Account u = new Account();
        u.setEmployee_acount(true);
        u.setUsername("1stmato");
        u.setPassword("password");
        u.setUi_lang("français");
        Account u2 = new Account();
        u2.setEmployee_acount(false);
        u2.setUsername("newUser");
        u2.setPassword("password2");
        u2.setUi_lang("english");
        p.create(u);
        p.create(u2);
        List<Account> accounts = p.findAll();
        System.out.println(accounts);
        Assert.assertEquals(accounts.size(), 2);
    }

    /**
     * Test of findById method, of class UserPersistence.
     */
    @org.junit.Test
    public void testFindById() {
        System.out.println("findById");
        Account u = new Account();
        u.setEmployee_acount(true);
        u.setUsername("1stmato");
        u.setPassword("password");
        u.setUi_lang("français");
        Account savedU = p.create(u);
        Account gotU = p.findById(savedU.getId());
        Assert.assertEquals(savedU.getId(), gotU.getId());
        Assert.assertEquals(savedU.getUsername(), gotU.getUsername());
        Assert.assertEquals(savedU.getPassword(), gotU.getPassword());
        Assert.assertEquals(savedU.getUi_lang(), gotU.getUi_lang());
    }
    
}
