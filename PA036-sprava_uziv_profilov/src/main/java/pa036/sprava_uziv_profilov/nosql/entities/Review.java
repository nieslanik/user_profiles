/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa036.sprava_uziv_profilov.nosql.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import pa036.profiles.entity.*;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.mongojack.ObjectId;
/**
 *
 * @author Mojmir
 */

public class Review {

    private String id;
    private String text;
    
    private int restaurant_id;
    private int user_id;

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

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


    public String getText() {
        return text;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public int getUser_id() {
        return user_id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id.hashCode();
        hash = 97 * hash + this.restaurant_id;
        hash = 97 * hash + this.user_id;
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
        if (this.user_id != other.user_id) {
            return false;
        }
        if(!(this.text).equals(other.text))
        {
            return false;
        }
        return true;
    }
    
}
