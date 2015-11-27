package cz.muni.fi.pa165.dto;

/**
 * Data Transfer object for authentication of Member entity
 * 
 * @author Juraj Tomko 
 */
public class MemberAuthenticateDTO {

    private Long memberId;
    private String password;

    public MemberAuthenticateDTO() {
    }

    public MemberAuthenticateDTO(Long memberId, String password) {
        this.memberId = memberId;
        this.password = password;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
