package cz.muni.fi.pa036.sql.service;

import cz.muni.fi.pa036.dao.RestaurantPersistenceDao;
import cz.muni.fi.pa036.dao.ReviewPersistenceDao;
import cz.muni.fi.pa036.entity.Restaurant;
import cz.muni.fi.pa036.entity.Review;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by xnieslan on 06.05.2016.
 */
public class RestaurantServiceSQLImpl implements RestaurantServiceSQL {

    private RestaurantPersistenceDao restaurantPersistenceDao;

    private ReviewPersistenceDao reviewPersistenceDao;


    public RestaurantPersistenceDao getRestaurantPersistenceDao() {
        return restaurantPersistenceDao;
    }

    public void setRestaurantPersistenceDao(RestaurantPersistenceDao restaurantPersistenceDao) {
        this.restaurantPersistenceDao = restaurantPersistenceDao;
    }

    @Override
    public Restaurant create(Restaurant r) {
        return restaurantPersistenceDao.create(r);
    }

    @Override
    public void remove(int restaurantId) {
        restaurantPersistenceDao.delete(restaurantPersistenceDao.findById(restaurantId));
    }

    @Override
    public void update(Restaurant r) {
        restaurantPersistenceDao.update(r);
    }

    @Override
    public void addReview(int restaurantId, Review r, Long userId) {

        r.setRestaurant_id(restaurantId);
        r.setUser_id(userId.intValue());
        reviewPersistenceDao.create(r);

    }

    @Override
    public void removeReview(int restaurantId, Long reviewId) {
        reviewPersistenceDao.delete(reviewPersistenceDao.findById(reviewId));
    }

    @Override
    public double getRating(int restaurantId) {
        throw new UnsupportedOperationException();

//        return restaurantPersistenceDao.getRating(restaurantId);
    }



    @Override
    public Restaurant findById(int id) {
        return restaurantPersistenceDao.findById(id);
    }

    @Override
    public Restaurant findByName(String name) {
        return restaurantPersistenceDao.findByName(name);
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantPersistenceDao.findAll();
    }

    @Override
    public void RemoveAll() {
    }

    @Override
    public List<Restaurant> getTopRestaurants() {
        return null;
    }
}
