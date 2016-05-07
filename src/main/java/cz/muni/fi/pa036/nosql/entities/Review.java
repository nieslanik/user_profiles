/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.nosql.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.mongojack.DBRef;
import org.mongojack.ObjectId;
/**
 *
 * @author Mojmir
 */

public class Review {

    private String id;
    private String text;
    
    private String restaurant_id;
    private String userId;
    private int rating;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) 
    {
        this.rating = rating;
    }

    /*@ObjectId
    @JsonProperty("_id")*/
    public String getId() 
    {
        return id;
    }
    /*@ObjectId
    @JsonProperty("_id")*/
    public void setId(String id) 
    {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public void setUserId(String user_id) {
        this.userId = user_id;
    }


    public String getText() {
        return text;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id.hashCode();
        hash = 97 * hash + this.restaurant_id.hashCode();
        hash = 97 * hash + this.userId.hashCode();
        hash = 97 * hash + this.text.hashCode();
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
        final Review other = (Review) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.restaurant_id != other.restaurant_id) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        if(!(this.text).equals(other.text))
        {
            return false;
        }
        return true;
    }
    
}
