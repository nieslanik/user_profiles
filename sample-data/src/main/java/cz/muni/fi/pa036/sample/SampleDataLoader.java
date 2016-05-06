package cz.muni.fi.pa036.sample;

import cz.muni.fi.pa036.dao.AccountPersistenceDao;
import cz.muni.fi.pa036.dao.RestaurantPersistenceDao;
import cz.muni.fi.pa036.dao.ReviewPersistenceDao;
import cz.muni.fi.pa036.entity.Account;
import cz.muni.fi.pa036.entity.Restaurant;
import cz.muni.fi.pa036.entity.Review;
import java.sql.Timestamp;
import java.util.Arrays;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class SampleDataLoader {

    @Inject
   private AccountPersistenceDao ap;

    @Inject
    private RestaurantPersistenceDao rp;

    @Inject
    private ReviewPersistenceDao rep;

    public void createSampleData() {
        //account("Alojz Novak",)

    }

    private void account(String username, String userdbname, String password, String ui_settings,char[] ui_lang,char[] acces_rights, Timestamp logon_last_timestamp, int logon_status,int company_key,boolean employee) {
        Account acc = new Account();
        acc.setPassword(password);
        acc.setUsername(username);
        acc.setUsername_db(userdbname);
        acc.setLogon_status(logon_status);
        acc.setUi_settings(ui_settings);
        acc.setUi_lang(ui_lang);
        acc.setLogon_last_timestamp(logon_last_timestamp);
        acc.setAcces_rights(acces_rights);
        acc.setCompany_key(company_key);
        acc.setEmployee_acount(employee);
        ap.create(acc);
    }

    private void restaurant(int id, String name) {
       Restaurant res =new Restaurant();
       res.setId(id);
       res.setName(name);
       rp.create(res);
    }

    private void review(int id, String text, int restaurant_id,int user_id) {
        Review rev = new Review();
        rev.setId(id);
        rev.setRestaurant_id(restaurant_id);
        rev.setText(text);
        rev.setUser_id(user_id);
        rep.create(rev);
    }
}
