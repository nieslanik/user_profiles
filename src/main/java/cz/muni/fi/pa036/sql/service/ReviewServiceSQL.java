package cz.muni.fi.pa036.sql.service;

import cz.muni.fi.pa036.sql.entities.Review;

import java.util.List;

/**
 * Created by xnieslan on 07.05.2016.
 */
public interface ReviewServiceSQL {

    Review create(Review f);

    void delete(Review f);

    void update(Review f);

    List<Review> findAll();

    Review findById(int id);

    List<Review> findByRestaurantId(int id);

    List<Review> findByAccountId(int id);

}
