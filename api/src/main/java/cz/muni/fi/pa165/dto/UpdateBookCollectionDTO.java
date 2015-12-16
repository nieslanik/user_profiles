package cz.muni.fi.pa165.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * DTO for BookCollection updates
 *
 * @author Michael Simacek
 */
public class UpdateBookCollectionDTO {

    @NotNull
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;
    private List<Long> bookIds = new ArrayList<>();

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

    public List<Long> getBookIds() {
        return bookIds;
    }

    public void setBookIds(List<Long> bookIds) {
        this.bookIds = bookIds;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bookIds == null) ? 0 : bookIds.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof UpdateBookCollectionDTO))
            return false;
        UpdateBookCollectionDTO other = (UpdateBookCollectionDTO) obj;
        if (bookIds == null) {
            if (other.bookIds != null)
                return false;
        } else if (!bookIds.equals(other.bookIds))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
}
