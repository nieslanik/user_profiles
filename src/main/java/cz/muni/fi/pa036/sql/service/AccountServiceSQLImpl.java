package cz.muni.fi.pa036.sql.service;

import cz.muni.fi.pa036.sql.dao.AccountPersistenceDao;
import cz.muni.fi.pa036.sql.entities.Account;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by xnieslan on 06.05.2016.
 */
public class AccountServiceSQLImpl implements AccountServiceSQL {
    
    @Autowired
    private AccountPersistenceDao accountPersistenceDao;

    public AccountPersistenceDao getAccountPersistenceDao() {
        return accountPersistenceDao;
    }

    public void setAccountPersistenceDao(AccountPersistenceDao accountPersistenceDao) {
        this.accountPersistenceDao = accountPersistenceDao;
    }

    @Override
    public Account registerAccount(Account account, String unencryptedPassword) {
        account.setPassword(unencryptedPassword);
        return accountPersistenceDao.create(account);
    }

    @Override
    public List<Account> findAll() {
        return accountPersistenceDao.findAll();
    }

    @Override
    public Account findById(int accountId) {
        return accountPersistenceDao.findById(accountId);
    }

    @Override
    public boolean authenticate(Account account, String password) {
        return accountPersistenceDao.findByName(account.getUsername()).getPassword().equals(password);
    }

    @Override
    public void update(Account account) {

        accountPersistenceDao.update(account);
    }

    @Override
    public void delete(Account account) {

        accountPersistenceDao.delete(account);
    }

    @Override
    public Account findByName(String name) {

        return accountPersistenceDao.findByName(name);
    }
}
