package cz.muni.fi.pa165.dto;

/**
 * Data Transfer object for authentication of Member entity
 * 
 * @author Juraj Tomko 
 */
public class MemberAuthenticateDTO {

    private String memberEmail;
    private String password;

    public MemberAuthenticateDTO() {
    }

    public MemberAuthenticateDTO(String memberEmail, String password) {
        this.memberEmail = memberEmail;
        this.password = password;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
