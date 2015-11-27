package cz.muni.fi.pa165.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import cz.muni.fi.pa165.enums.BookState;

/**
 * Entity representing a single book in a library
 *
 * @author Michael Simacek
 *
 */
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String authorName;

    @Column(nullable = false)
    private Long isbn;

    @Column(nullable = false)
    private BookState state = BookState.NEW;

    @ManyToMany(mappedBy = "books")
    private List<BookCollection> collections = new ArrayList<>();

    @OneToMany
    // OneToMany because we want to keep history of loans
    private List<Loan> loans = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public BookState getState() {
        return state;
    }

    public void setState(BookState state) {
        this.state = state;
    }

    public List<BookCollection> getCollections() {
        return Collections.unmodifiableList(collections);
    }

    public void addCollection(BookCollection collection) {
        collections.add(collection);
    }

    public List<Loan> getLoans() {
        return Collections.unmodifiableList(loans);
    }

    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public void setCollections(List<BookCollection> collections) {
        this.collections = collections;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.getAuthorName() == null) ? 0 : this.getAuthorName().hashCode());
        result = prime * result + ((this.getIsbn() == null) ? 0 : this.getIsbn().hashCode());
        result = prime * result + ((this.getName() == null) ? 0 : this.getName().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Book))
            return false;
        Book other = (Book) obj;
        if (this.getAuthorName() == null) {
            if (other.getAuthorName() != null)
                return false;
        } else if (!this.getAuthorName().equals(other.getAuthorName()))
            return false;
        if (this.getIsbn() == null) {
            if (other.getIsbn() != null)
                return false;
        } else if (!this.getIsbn().equals(other.getIsbn()))
            return false;
        if (this.getName() == null) {
            if (other.getName() != null)
                return false;
        } else if (!this.getName().equals(other.getName()))
            return false;
        return true;
    }
}
