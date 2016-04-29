/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa036.sprava_uziv_profilov.nosql.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.mongojack.DBRef;
import org.mongojack.MongoCollection;
import org.mongojack.ObjectId;

/**
 *
 * @author Mato
 */
@MongoCollection(name="Restaurants")
public class Restaurant {
    private String id;

    private String name;

    private List<Review>reviews = new ArrayList<>();//List<DBRef<Review, String>> reviews;

    public List<Review> getReviews()//List<DBRef<Review, String>> getReviews() {
    {
        return reviews;
    }

    public void setReviews(List<Review> reviews)//<DBRef<Review, String>> reviews) {
    {
        this.reviews = reviews;
    }
    
    @ObjectId
    @JsonProperty("_id")
    public String getId() 
    {
        return id;
    }
    @ObjectId
    @JsonProperty("_id")
    public void setId(String id) 
    {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.id.hashCode();
        hash = 17 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Restaurant other = (Restaurant) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    
}
