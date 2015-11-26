package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.enums.BookState;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author xkubist
 * @email m.kubistel@gmail.com
 */
public class LoanDTO {
    
    private Long id;
    private Long memberId;
    private Long bookId;
    private Date loanDate;
    private Boolean returned;
    private Date returnDate;
    private BookState returnBookState;
    
    public Long getId() {
        return id;
    }

    public void setId(Long loanId) {
        this.id = loanId;
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

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Boolean getReturned() {
        return returned;
    }

    public void setReturned(Boolean returned) {
        this.returned = returned;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public BookState getReturnBookState() {
        return returnBookState;
    }

    public void setReturnBookState(BookState returnBookState) {
        this.returnBookState = returnBookState;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
        result = prime * result + ((loanDate == null) ? 0 : loanDate.hashCode());
        result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LoanDTO other = (LoanDTO) obj;
        if (bookId == null) {
            if (other.bookId != null)
                return false;
        } else if (!bookId.equals(other.bookId))
            return false;
        if (loanDate == null) {
            if (other.loanDate != null)
                return false;
        } else if (!loanDate.equals(other.loanDate))
            return false;
        if (memberId == null) {
            if (other.memberId != null)
                return false;
        } else if (!memberId.equals(other.memberId))
            return false;
        return true;
    }

}
