/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa036.sprava_uziv_profilov.nosql.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;
import org.mongojack.Id;
import org.mongojack.MongoCollection;
import org.mongojack.ObjectId;


/**
 *
 * @author Mojmir
 */
@MongoCollection(name="Users")
public class Account {


    private String id;


    private int company_key;

    private String username;

    private String password;

    private String ui_lang;

    private String acces_rights;

    private String ui_settings;

    private String username_db;

    private boolean employee_acount;

    private Timestamp logon_last_timestamp;

    private int logon_status;

    @ObjectId
    @JsonProperty("_id")
    public String getId() 
    {
        return id;
    }
    @ObjectId
    @JsonProperty("_id")
    public void setId(String id) 
    {
        this.id = id;
    }

    public int getCompany_key() {
        return company_key;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUi_lang() {
        return ui_lang;
    }

    public String getAcces_rights() {
        return acces_rights;
    }

    public String getUi_settings() {
        return ui_settings;
    }

    public String getUsername_db() {
        return username_db;
    }

    public boolean isEmployee_acount() {
        return employee_acount;
    }

    public Timestamp getLogon_last_timestamp() {
        return logon_last_timestamp;
    }

    public int getLogon_status() {
        return logon_status;
    }


    public void setCompany_key(int company_key) {
        this.company_key = company_key;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUi_lang(String ui_lang) {
        this.ui_lang = ui_lang;
    }

    public void setAcces_rights(String access_rights) {
        this.acces_rights = access_rights;
    }

    public void setUi_settings(String ui_settings) {
        this.ui_settings = ui_settings;
    }

    public void setUsername_db(String username_db) {
        this.username_db = username_db;
    }

    public void setEmployee_acount(boolean employee_acount) {
        this.employee_acount = employee_acount;
    }

    public void setLogon_last_timestamp(Timestamp logon_last_timestamp) {
        this.logon_last_timestamp = logon_last_timestamp;
    }

    public void setLogon_status(int logon_status) {
        this.logon_status = logon_status;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id.hashCode();
        hash = 89 * hash + this.company_key;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.company_key != other.company_key) {
            return false;
        }
        return true;
    }
    
    
}
