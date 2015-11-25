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
    private Date loanDate;
    private Boolean returned;
    private Date returnDate;
    private BookState returnBookState;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.loanDate);
        hash = 23 * hash + Objects.hashCode(this.returned);
        hash = 23 * hash + Objects.hashCode(this.returnDate);
        hash = 23 * hash + Objects.hashCode(this.returnBookState);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LoanDTO other = (LoanDTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.loanDate, other.loanDate)) {
            return false;
        }
        if (!Objects.equals(this.returned, other.returned)) {
            return false;
        }
        if (!Objects.equals(this.returnDate, other.returnDate)) {
            return false;
        }
        if (this.returnBookState != other.returnBookState) {
            return false;
        }
        return true;
    }
}
