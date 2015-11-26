package cz.muni.fi.pa165.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


/**
 * Entity representing a collection of books in a library
 *
 * @author Jakub Peschel <jakub.peschel@studentagency.cz>
 */
@Entity
public class BookCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    @ManyToMany
    private Set<Book> books = new HashSet<>();

    //<editor-fold defaultstate="collapsed" desc="GET/SET">
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

    public Set<Book> getBooks() {
        return Collections.unmodifiableSet(books);
    }

    public void addBook(Book book) {
        this.books.add(books);
    }
    
    public void removeBook(Book book) {
        this.books.remove(books);
    }

    //</editor-fold>

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.getId());
        hash = 37 * hash + Objects.hashCode(this.getName());
        hash = 37 * hash + Objects.hashCode(this.getBooks());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof BookCollection))
            return false;
        final BookCollection other = (BookCollection) obj;
        if (!Objects.equals(this.getId(), other.getId()))
            return false;
        if (!Objects.equals(this.getName(), other.getName()))
            return false;
        if (!Objects.equals(this.getBooks(), other.getBooks()))
            return false;
        return true;
    }


}
