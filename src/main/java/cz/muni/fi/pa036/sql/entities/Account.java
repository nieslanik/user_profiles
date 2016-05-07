/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.sql.entities;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Mojmir
 */
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int company_key;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private char[] ui_lang;
    @Column
    private char[] acces_rights;
    @Column
    private String ui_settings;
    @Column
    private String username_db;
    @Column
    private boolean employee_acount;
    @Column
    private Timestamp logon_last_timestamp;
    @Column
    private int logon_status;

    public int getId() {
        return id;
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

    public char[] getUi_lang() {
        return ui_lang;
    }

    public char[] getAcces_rights() {
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

    public void setId(int id) {
        this.id = id;
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

    public void setUi_lang(char[] ui_lang) {
        this.ui_lang = ui_lang;
    }

    public void setAcces_rights(char[] acces_rights) {
        this.acces_rights = acces_rights;
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
        hash = 89 * hash + this.id;
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
