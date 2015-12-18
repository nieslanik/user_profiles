package cz.muni.fi.pa165.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cz.muni.fi.pa165.enums.BookState;

/**
 * Data Transfer object for Book entity
 * 
 * @author Michael Simacek
 *
 */
public class BookDTO {

    private Long id;
    private String name;
    private String authorName;
    private Long isbn;
    private BookState state;

    @JsonIgnore
    private List<LoanDTO> loans;

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

    public List<LoanDTO> getLoans() {
        return loans;
    }

    public void setLoans(List<LoanDTO> loans) {
        this.loans = loans;
    }

    public boolean isLoaned() {
        for (LoanDTO loan: loans) {
            if (!loan.getReturned()) {
                return true;
            }
        }
        return false;
    }

    @JsonIgnore
    public List<BookState> getPossibleStateTransitions() {
        List<BookState> availableStates = new ArrayList<>();
        for (BookState s : BookState.values()) {
            if (s.compareTo(state) >= 0) {
                availableStates.add(s);
            }
        }
        return availableStates;
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
        if (!(obj instanceof BookDTO))
            return false;
        BookDTO other = (BookDTO) obj;
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
