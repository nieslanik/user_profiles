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

import cz.muni.fi.pa036.sql.service.AccountServiceSQL;
import cz.muni.fi.pa036.sql.service.RestaurantServiceSQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author xnieslan
 */
@Service
public class AccountFacadeImpl implements AccountFacade{

    @Autowired
    private AccountService accountService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantServiceSQL restaurantServiceSQL;

    @Autowired
    private AccountServiceSQL accountServiceSQL;

    public AccountService getAccountService() {
        return accountService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public RestaurantService getRestaurantService() {
        return restaurantService;
    }

    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    public RestaurantServiceSQL getRestaurantServiceSQL() {
        return restaurantServiceSQL;
    }

    public void setRestaurantServiceSQL(RestaurantServiceSQL restaurantServiceSQL) {
        this.restaurantServiceSQL = restaurantServiceSQL;
    }

    public AccountServiceSQL getAccountServiceSQL() {
        return accountServiceSQL;
    }

    public void setAccountServiceSQL(AccountServiceSQL accountServiceSQL) {
        this.accountServiceSQL = accountServiceSQL;
    }

    @Override
    public Boolean login(String username, String password) {
        if(ChooseDB.noSQL) {
            Account account = accountService.findByName(username);
            if (account.getPassword().equals(password)) {
                Calendar calendar = Calendar.getInstance();
                //set timestamp
                Timestamp logon_last_timestamp = new java.sql.Timestamp(calendar.getTime().getTime());
                account.setLogon_last_timestamp(logon_last_timestamp);
                //set logon status
                account.setLogon_status(1);
                return true;
            } else {
                return false;
            }
        }else{
            cz.muni.fi.pa036.sql.entities.Account account = accountServiceSQL.findByName(username);
            if (account.getPassword().equals(password)) {
                Calendar calendar= Calendar.getInstance();
                //set timestamp
                Timestamp logon_last_timestamp = new java.sql.Timestamp(calendar.getTime().getTime());
                account.setLogon_last_timestamp(logon_last_timestamp);
                //set logon status
                account.setLogon_status(1);
                return true;
            } else {
                return false;
            }


        }
    }

    @Override
    public Boolean logout(String username) {
        if (ChooseDB.noSQL) {
            Account account = accountService.findByName(username);
            if (account.getLogon_status() == 1) {
                account.setLogon_status(0);
                return true;
            } else {
                return false;
            }
        }else{
            cz.muni.fi.pa036.sql.entities.Account account = accountServiceSQL.findByName(username);
            if (account.getLogon_status() == 1) {
                account.setLogon_status(0);
                return true;
            } else {
                return false;
            }
        }

    }

    @Override
    public Boolean register(String username, String password, Boolean employee) {
        if (ChooseDB.noSQL) {
            Account account = new Account();
            account.setUsername(username);
            account.setPassword(password);
            account.setEmployee_acount(employee);

            Account compare = accountService.findByName(username);

            if (compare == null) {
                accountService.registerAccount(account, password);
                return true;
            } else {
                return false;
            }
        }else{
            cz.muni.fi.pa036.sql.entities.Account account = new cz.muni.fi.pa036.sql.entities.Account();
            account.setUsername(username);
            account.setPassword(password);
            account.setEmployee_acount(employee);

            cz.muni.fi.pa036.sql.entities.Account compare = accountServiceSQL.findByName(username);

            if (compare == null) {
                accountServiceSQL.registerAccount(account, password);
                return true;
            } else {
                return false;
            }
        }
    }
    @Override
    public int userStatus(String username) {
        if (ChooseDB.noSQL){
            Account account = accountService.findByName(username);
            return account.getLogon_status();
        }else{
            cz.muni.fi.pa036.sql.entities.Account account = accountServiceSQL.findByName(username);
            return account.getLogon_status();
        }
    }

    @Override
    public int numberOfLogin() {
        if (ChooseDB.noSQL) {
            List<Account> accounts = accountService.findAll();

            int result = 0;

            for (Account account : accounts) {
                if (account.getLogon_status() == 1) {
                    result++;
                }
            }
            return result;

        }else{

            List<cz.muni.fi.pa036.sql.entities.Account> accounts = accountServiceSQL.findAll();

            int result = 0;

            for (cz.muni.fi.pa036.sql.entities.Account account : accounts) {
                if (account.getLogon_status() == 1) {
                    result++;
                }
            }
            return result;
    }
    }
}
