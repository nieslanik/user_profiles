/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.sql.dao;

import cz.muni.fi.pa036.sql.entities.Review;
import java.util.List;

/**
 *
 * @author Mojmir
 */
public interface ReviewPersistenceDao {
    Review create(Review f);

    void delete(Review f);

    void update(Review f);
    
    List<Review> findAll();

    Review findById(int id);
    
    List<Review> findByRestaurantId(int id);
    
    List<Review> findByAccountId(int id);
}
