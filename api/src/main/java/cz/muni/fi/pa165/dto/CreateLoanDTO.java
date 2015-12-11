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
        result = prime * result + ((getBookId() == null) ? 0 : getBookId().hashCode());
        result = prime * result + ((getMemberId() == null) ? 0 : getMemberId().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof CreateLoanDTO)) {
            return false;
        }
        CreateLoanDTO other = (CreateLoanDTO) obj;
        if (getBookId() == null) {
            if (other.getBookId() != null) {
                return false;
            }
        } else if (!getBookId().equals(other.getBookId())) {
            return false;
        }
        if (getMemberId() == null) {
            if (other.getMemberId() != null) {
                return false;
            }
        } else if (!getMemberId().equals(other.getMemberId())) {
            return false;
        }
        return true;
    }
}
