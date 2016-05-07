/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.facade;

import cz.muni.fi.pa036.nosql.entities.Account;
import cz.muni.fi.pa036.nosql.service.AccountService;
import cz.muni.fi.pa036.nosql.service.RestaurantService;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author xnieslan
 */
public class AccountFacadeImpl implements AccountFacade{

    @Autowired
    private AccountService accountService;

    @Autowired
    private RestaurantService restaurantService;    


    @Override
    public Boolean login(String username, String password) {
        Account account = accountService.findByName(username);
        if(account.getPassword().equals(password)){
            Calendar calendar = Calendar.getInstance();
            //set timestamp
            Timestamp logon_last_timestamp = new java.sql.Timestamp(calendar.getTime().getTime());
            account.setLogon_last_timestamp(logon_last_timestamp);
            //set logon status
            account.setLogon_status(1);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean logout(String username) {
        Account account = accountService.findByName(username);
        if(account.getLogon_status() == 1){
            account.setLogon_status(0);
            return true;
        }else{
            return false;
        }

    }

    @Override
    public Boolean register(String username, String password, Boolean employee) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setEmployee_acount(employee);

        Account compare = accountService.findByName(username);

        if (compare == null){
            accountService.registerAccount(account, password);
            return true;
        }else{
            return false;
        }
    }
    @Override
    public int userStatus(String username) {
        Account account = accountService.findByName(username);
        return account.getLogon_status();
    }

    @Override
    public int numberOfLogin() {
        List<Account> accounts = accountService.findAll();

        int result = 0;

        for (Account account : accounts){
            if(account.getLogon_status() == 1){
                result++;
            }
        }
        return result;
    }

}
