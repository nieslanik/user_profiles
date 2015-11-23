package cz.muni.fi.pa165.dto;

/**
 * Created by Juraj on 11/23/2015.
 */
public class NewLoanDTO {

    private Long memberId;
    private Long loanId;

    public NewLoanDTO() {
    }

    public NewLoanDTO(Long memberId, Long loanId) {
        this.memberId = memberId;
        this.loanId = loanId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }
}
