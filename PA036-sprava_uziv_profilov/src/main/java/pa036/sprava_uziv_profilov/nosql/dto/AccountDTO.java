/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa036.sprava_uziv_profilov.nosql.dto;

import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author xnieslan
 */
public class AccountDTO {
    
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCompany_key() {
        return company_key;
    }

    public void setCompany_key(int company_key) {
        this.company_key = company_key;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUi_lang() {
        return ui_lang;
    }

    public void setUi_lang(String ui_lang) {
        this.ui_lang = ui_lang;
    }

    public String getAcces_rights() {
        return acces_rights;
    }

    public void setAcces_rights(String acces_rights) {
        this.acces_rights = acces_rights;
    }

    public String getUi_settings() {
        return ui_settings;
    }

    public void setUi_settings(String ui_settings) {
        this.ui_settings = ui_settings;
    }

    public String getUsername_db() {
        return username_db;
    }

    public void setUsername_db(String username_db) {
        this.username_db = username_db;
    }

    public boolean isEmployee_acount() {
        return employee_acount;
    }

    public void setEmployee_acount(boolean employee_acount) {
        this.employee_acount = employee_acount;
    }

    public Timestamp getLogon_last_timestamp() {
        return logon_last_timestamp;
    }

    public void setLogon_last_timestamp(Timestamp logon_last_timestamp) {
        this.logon_last_timestamp = logon_last_timestamp;
    }

    public int getLogon_status() {
        return logon_status;
    }

    public void setLogon_status(int logon_status) {
        this.logon_status = logon_status;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + this.company_key;
        hash = 37 * hash + Objects.hashCode(this.username);
        hash = 37 * hash + Objects.hashCode(this.password);
        hash = 37 * hash + Objects.hashCode(this.ui_lang);
        hash = 37 * hash + Objects.hashCode(this.acces_rights);
        hash = 37 * hash + Objects.hashCode(this.ui_settings);
        hash = 37 * hash + Objects.hashCode(this.username_db);
        hash = 37 * hash + (this.employee_acount ? 1 : 0);
        hash = 37 * hash + Objects.hashCode(this.logon_last_timestamp);
        hash = 37 * hash + this.logon_status;
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
        final AccountDTO other = (AccountDTO) obj;
        if (this.company_key != other.company_key) {
            return false;
        }
        if (this.employee_acount != other.employee_acount) {
            return false;
        }
        if (this.logon_status != other.logon_status) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.ui_lang, other.ui_lang)) {
            return false;
        }
        if (!Objects.equals(this.acces_rights, other.acces_rights)) {
            return false;
        }
        if (!Objects.equals(this.ui_settings, other.ui_settings)) {
            return false;
        }
        if (!Objects.equals(this.username_db, other.username_db)) {
            return false;
        }
        if (!Objects.equals(this.logon_last_timestamp, other.logon_last_timestamp)) {
            return false;
        }
        return true;
    }
    
    
    
}
