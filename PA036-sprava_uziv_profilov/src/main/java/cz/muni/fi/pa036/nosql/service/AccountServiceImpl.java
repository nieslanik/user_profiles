/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.nosql.service;

import cz.muni.fi.pa036.nosq.entities.Account;
import cz.muni.fi.pa036.nosql.persistence.UserPersistence;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author xnieslan
 */
public class AccountServiceImpl implements AccountService{

    @Autowired
    private UserPersistence userPersistence;

    public UserPersistence getUserPersistence() {
        return userPersistence;
    }

    public void setUserPersistence(UserPersistence userPersistence) {
        this.userPersistence = userPersistence;
    }

    @Override
    public Account registerAccount(Account account, String unencryptedPassword) {
        return userPersistence.create(account);
    }

    @Override
    public List<Account> findAll() {
        return userPersistence.findAll();
    }

    @Override
    public Account findById(String accountId) {
        return userPersistence.findById(accountId);
    }

    @Override
    public boolean authenticate(Account account, String password) {
        return account.getPassword().equals(password);
    }

    @Override
    public void update(Account account) {
        userPersistence.update(account);
    }

    @Override
    public void delete(Account account) {
        userPersistence.delete(account);
    }

    @Override
    public Account findByName(String name) {
        return userPersistence.findByName(name);
    }

}
