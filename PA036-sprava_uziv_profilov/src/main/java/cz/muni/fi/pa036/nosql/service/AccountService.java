/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.nosql.service;

import cz.muni.fi.pa036.nosq.entities.Account;
import java.util.List;


/**
 *
 * @author xnieslan
 */
public interface AccountService {

    
    Account registerAccount(Account account, String unencryptedPassword);

    List<Account> findAll();

    Account findById(String accountId);

    boolean authenticate(Account account, String password);

    void update(Account account);

    void delete(Account account);
    
    Account findByName(String name); 

}
