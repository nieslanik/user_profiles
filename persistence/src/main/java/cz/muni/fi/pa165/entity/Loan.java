package cz.muni.fi.pa165.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

import cz.muni.fi.pa165.enums.BookState;

/**
 * +[Loan|loanDate:Date;returned:Boolean;returnDate:Date;returnedBookState:BookState]
 * +[Loan]++-1>[Book]
 *
 * @author xkubist
 */
@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date loanDate;
    @Column(nullable = false)
    private Boolean returned;
    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date returnDate;
    @Column(nullable = false)
    private BookState returnBookState;

    @ManyToOne
    private Book book;

    public void setBook(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getDate() {
        return this.loanDate;
    }

    public Boolean isReturned() {
        return this.returned;
    }

    public void setReturned(Boolean returned) {
        this.returned = returned;
    }

    public void returnLoan() {
        this.returned = true;
    }

    public Date getReturnDate() {
        return this.returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public BookState getReturnBookState() {
        return this.returnBookState;
    }

    public void setReturnBookState(BookState returnBookState) {
        this.returnBookState = returnBookState;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((this.getDate() == null) ? 0 : this.getDate().hashCode());
        result = prime * result + ((this.isReturned() == null) ? 0 : this.isReturned().hashCode());
        result = prime * result + ((this.getReturnDate() == null) ? 0 : this.getReturnDate().hashCode());
        result = prime * result + ((this.getReturnBookState() == null) ? 0 : this.getReturnBookState().hashCode());
        result = prime * result + ((this.getBook() == null) ? 0 : this.getBook().hashCode());
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
        if (!(obj instanceof Loan)) {
            return false;
        }
        Loan other = (Loan) obj;
        if (this.getDate() == null && other.getDate() != null) {
            return false;
        } else if (!this.getDate().equals(other.getDate())) {
            return false;
        }
        if (this.isReturned() == null && other.isReturned() != null) {
            return false;
        } else if (isReturned() != other.isReturned()) {
            return false;
        }
        if (this.getReturnDate() == null && other.getReturnDate() != null) {
            return false;
        } else if (!this.getReturnDate().equals(other.getReturnDate())) {
            return false;
        }
        if (this.getReturnBookState() == null && other.getReturnBookState() != null) {
            return false;
        } else if (!this.getReturnBookState().equals(other.getReturnBookState())) {
            return false;
        }
        if (this.getBook() == null && other.getBook() != null) {
            return false;
        } else if (!this.getBook().equals(other.getBook())) {
            return false;
        }
        return true;
    }
}
