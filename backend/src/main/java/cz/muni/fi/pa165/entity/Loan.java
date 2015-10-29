package cz.muni.fi.pa165.entity;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

 /**
  * +[Loan|loanDate:Date;returned:Boolean;returnDate:Date;returnedBookState:BookState]
  * +[Loan]++-1>[Book]
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
    @Column(nullable = false)
    
    @ManyToOne
    private Book book;

    public void setBook(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }
    
    public void setId(Long id){
        this.id=id;
    }
    
    public Long getId(){
        return this.id;
    }
    
    public void setDate(Date loanDate){
        this.loanDate=loanDate;
    }
     public Date getDate(){
        return this.loanDate;
    }
     
    public Boolean isReturned(){
        return this.returned;
    }
    public void returnLoan(){
        this.returned=true;
    }
    
    public Date getReturnDate(){
        return this.returnDate;
    }
    public void setReturnDate(Date returnDate){
        this.returnDate=returnDate;
    }
    
    public BookState getReturnBookState(){
        return this.returnBookState;
    }
    public void setReturnBookState(Date returnBookState){
        this.returnDate=returnBookState;
    }
    
    
    
  
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((loanDate == null) ? 0 : loanDate.hashCode());
        result = prime * result + ((returned == null) ? 0 : returned.hashCode());
        result = prime * result + ((returnDate == null) ? 0 : returnDate.hashCode());
        result = prime * result + ((returnBookState == null) ? 0 : returnBookState.hashCode());
        result = prime * result + ((book == null) ? 0 : book.hashCode());
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
        if (loanDate == null && other.loanDate != null) {
                return false;
            }
        else if (!loanDate.equals(other.loanDate)){
            return false; 
        }
        if (returned == null && other.returned != null) {
                return false;
        }
        else if(returned!=other.returned){
            return false; 
        }
        if (returnDate == null && other.returnDate != null) {
                return false;
        }
        else if(!returnDate.equals(other.returnDate)){
            return false; 
        }
        if (returnBookState == null && other.returnBookState != null) {
                return false;
        }
        else if(!returnBookState.equals(other.returnBookState)){
            return false;
        }
        if (book == null && other.book != null) {
                return false;
        }
        else if(!book.equals(other.book)){
            return false;
        }
        return true;
    }
}
