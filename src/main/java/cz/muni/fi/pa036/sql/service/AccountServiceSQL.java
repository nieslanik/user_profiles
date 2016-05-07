/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.sql.service;

import cz.muni.fi.pa036.sql.entities.Account;
import java.util.List;

/**
 *
 * @author xnieslan
 */
public interface AccountServiceSQL {
     Account registerAccount(Account account, String unencryptedPassword);
     List<Account> findAll();
     Account findById(int accountId);
     boolean authenticate(Account account, String password);
     void update(Account account);
     void delete(Account account);        
     Account findByName(String name);
}
