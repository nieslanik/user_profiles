package cz.muni.fi.pa165.dto;

import java.util.Date;

/**
 * Data Transfer object for create of Loan entity
 * 
 * @author Michael Simacek
 *
 */
public class CreateLoanDTO {

    private Long memberId;
    private Long bookId;
    private Date loanDate;
    private Date returnDate;

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
        result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
        result = prime * result + ((loanDate == null) ? 0 : loanDate.hashCode());
        result = prime * result + ((returnDate == null) ? 0 : returnDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass()) {
            return false;
        }
        CreateLoanDTO other = (CreateLoanDTO) obj;
        if (bookId == null) {
            if (other.bookId != null) {
                return false;
            }
        } else if (!bookId.equals(other.bookId)) {
            return false;
        }
        if (memberId == null) {
            if (other.memberId != null) {
                return false;
            }
        } else if (!memberId.equals(other.memberId)) {
            return false;
        }
        if (loanDate == null) {
            if (other.loanDate != null) {
                return false;
            }
        } else if (!loanDate.equals(other.loanDate)) {
            return false;
        }
        if (returnDate == null) {
            if (other.loanDate != null) {
                return false;
            }
        } else if (!returnDate.equals(other.returnDate)) {
            return false;
        }
        return true;
    }

}
