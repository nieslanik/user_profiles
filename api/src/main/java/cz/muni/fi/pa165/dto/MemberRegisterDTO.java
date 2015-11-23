package cz.muni.fi.pa165.dto;

/**
 * Created by Juraj on 11/23/2015.
 */
public class MemberRegisterDTO {

    private MemberDTO member;
    private String password;

    public MemberRegisterDTO() {
    }

    public MemberRegisterDTO(MemberDTO member, String password) {
        this.member = member;
        this.password = password;
    }

    public MemberDTO getMember() {
        return member;
    }

    public void setMember(MemberDTO member) {
        this.member = member;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
