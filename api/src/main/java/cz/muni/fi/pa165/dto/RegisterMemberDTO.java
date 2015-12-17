package cz.muni.fi.pa165.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Data Transfer object for registeration of Member entity
 * 
 * @author Juraj Tomko
 */
public class RegisterMemberDTO {
    @NotBlank
    @Size(max = 50)
    private String givenName;

    @NotBlank
    @Size(max = 50)
    private String surname;

    @NotNull
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 50)
    private String password;
    
    private boolean isAdmin;

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        return result;
    }

     @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof RegisterMemberDTO))
            return false;
        RegisterMemberDTO other = (RegisterMemberDTO) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        return true;
    } 
}




   