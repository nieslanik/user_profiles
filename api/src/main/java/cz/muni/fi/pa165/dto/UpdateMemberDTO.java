package cz.muni.fi.pa165.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Data Transfer object for registeration of Member entity
 *
 * @author Juraj Tomko
 */
public class UpdateMemberDTO {

    private Long id;

    @NotBlank
    @Size(max = 50)
    private String givenName;

    @NotBlank
    @Size(max = 50)
    private String surname;

    @NotBlank
    @Size(min = 6, max = 50)
    private String newPassword;

    private boolean isAdmin;

    public Long getId() {
        return id;
    }

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

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }


}
