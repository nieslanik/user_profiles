/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa036.sprava_uziv_profilov.nosql.facade;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pa036.sprava_uziv_profilov.nosql.entities.Account;
import pa036.sprava_uziv_profilov.nosql.service.AccountService;
import pa036.sprava_uziv_profilov.nosql.service.RestaurantService;

/**
 *
 * @author xnieslan
 */
@Service
@Transactional
public class AccountFacadeImpl implements AccountFacade{

    @Autowired
    private AccountService accountService;
    
    @Autowired
    private RestaurantService restaurantService;
    
    
    @Override
    public Boolean login(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean logout(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean register(String username, String password, Boolean employee) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setEmployee_acount(employee);
        
        List<Account> accounts = accountService.findByName(username);
        
        if (accounts.size() == 0){
            accountService.registerAccount(account, password);
            return true;
        }else{            
            return false;
        }
    }
    @Override
    public int userStatus(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int numberOfLogin() {
        List<Account> accounts = accountService.findAll();
        return accounts.size();
    }
    
}
