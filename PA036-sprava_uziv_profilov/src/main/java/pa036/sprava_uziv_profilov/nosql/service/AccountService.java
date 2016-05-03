/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa036.sprava_uziv_profilov.nosql.service;

import java.util.List;
import pa036.sprava_uziv_profilov.nosql.entities.Account;

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
    
    List<Account> findByName(String name); 

}
