/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.dao;

import cz.muni.fi.pa036.entity.Account;
import java.util.List;


/**
 *
 * @author Mojmir
 */
public interface AccountPersistenceDao {
   void create(Account u); 
   //return Account;
    
   void delete(Account u);

   void update(Account u);
    
   List<Account> findAll();

   Account findByName(String name);

   Account findById(Long id);
}
