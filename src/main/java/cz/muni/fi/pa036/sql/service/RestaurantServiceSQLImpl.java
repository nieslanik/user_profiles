package cz.muni.fi.pa036.sql.service;


import cz.muni.fi.pa036.sql.dao.RestaurantPersistenceDao;
import cz.muni.fi.pa036.sql.dao.ReviewPersistenceDao;
import cz.muni.fi.pa036.sql.entities.Restaurant;
import cz.muni.fi.pa036.sql.entities.Review;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by xnieslan on 06.05.2016.
 */
public class RestaurantServiceSQLImpl implements RestaurantServiceSQL {
    
    @Autowired
    private RestaurantPersistenceDao restaurantPersistenceDao;

    @Autowired
    private ReviewPersistenceDao reviewPersistenceDao;

    public ReviewPersistenceDao getReviewPersistenceDao() {
        return reviewPersistenceDao;
    }

    public void setReviewPersistenceDao(ReviewPersistenceDao reviewPersistenceDao) {
        this.reviewPersistenceDao = reviewPersistenceDao;
    }


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
    public void removeReview(int restaurantId, int reviewId) {
        reviewPersistenceDao.delete(reviewPersistenceDao.findById(reviewId));
    }

    @Override
    public double getRating(int restaurantId) {
        return restaurantPersistenceDao.getRating(restaurantId);
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
    public List<Restaurant> getTopRestaurants() {
        throw new UnsupportedOperationException();

    }
}
