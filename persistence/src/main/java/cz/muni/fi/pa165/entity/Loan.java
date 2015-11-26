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
    private boolean returned;
    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date returnDate;
    @Column(nullable = false)
    private BookState returnBookState;

    @ManyToOne
    private Member member;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

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

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
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
        result = prime * result + ((book == null) ? 0 : book.hashCode());
        result = prime * result + ((loanDate == null) ? 0 : loanDate.hashCode());
        result = prime * result + ((member == null) ? 0 : member.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Loan))
            return false;
        Loan other = (Loan) obj;
        if (book == null) {
            if (other.book != null)
                return false;
        } else if (!book.equals(other.getBook()))
            return false;
        if (loanDate == null) {
            if (other.loanDate != null)
                return false;
        } else if (!loanDate.equals(other.getLoanDate()))
            return false;
        if (member == null) {
            if (other.member != null)
                return false;
        } else if (!member.equals(other.getMember()))
            return false;
        return true;
    }

}
