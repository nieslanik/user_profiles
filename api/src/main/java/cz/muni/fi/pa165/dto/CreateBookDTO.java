package cz.muni.fi.pa165.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Transfer object for create of Book entity
 * 
 * @author Michael Simacek
 *
 */
public class CreateBookDTO {

    private String name;
    private String authorName;
    private Long isbn;
    private List<Long> collectionIds = new ArrayList<>();

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

    public List<Long> getCollectionIds() {
        return collectionIds;
    }

    public void setCollectionIds(List<Long> collectionIds) {
        this.collectionIds = collectionIds;
    }

    public void addCollectionId(Long id) {
        collectionIds.add(id);
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
        if (!(obj instanceof CreateBookDTO))
            return false;
        CreateBookDTO other = (CreateBookDTO) obj;
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
