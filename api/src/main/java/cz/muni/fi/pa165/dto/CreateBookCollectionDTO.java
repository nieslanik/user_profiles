/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.dto;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author xkubist
 * @email m.kubistel@gmail.com
 */
public class CreateBookCollectionDTO {

    private String name;
    private Set<BookDTO> books = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void addBook(BookDTO collection) {
        books.add(collection);
    }

    public void setCollections(Set<BookDTO> books) {
        this.books = books;
    }


    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.books);
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
        final CreateBookCollectionDTO other = (CreateBookCollectionDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.books, other.books)) {
            return false;
        }
        return true;
    }
    
}
