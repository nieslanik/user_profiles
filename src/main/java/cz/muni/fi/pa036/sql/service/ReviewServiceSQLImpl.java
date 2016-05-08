package cz.muni.fi.pa036.sql.service;

import cz.muni.fi.pa036.sql.dao.ReviewPersistenceDao;
import cz.muni.fi.pa036.sql.entities.Review;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Created by xnieslan on 07.05.2016.
 */
@Service
public class ReviewServiceSQLImpl implements ReviewServiceSQL {

    @Autowired
    private ReviewPersistenceDao reviewPersistenceDao;

    public ReviewPersistenceDao getReviewPersistenceDao() {
        return reviewPersistenceDao;
    }

    public void setReviewPersistenceDao(ReviewPersistenceDao reviewPersistenceDao) {
        this.reviewPersistenceDao = reviewPersistenceDao;
    }

    @Override
    public Review create(Review f) {
        return  reviewPersistenceDao.create(f);
    }

    @Override
    public void delete(Review f) {
        reviewPersistenceDao.delete(f);
    }

    @Override
    public void update(Review f) {
        reviewPersistenceDao.update(f);
    }

    @Override
    public List<Review> findAll() {
        return  reviewPersistenceDao.findAll();
    }

    @Override
    public Review findById(int id) {
        return reviewPersistenceDao.findById(id);
    }

    @Override
    public List<Review> findByRestaurantId(int id) {
        return reviewPersistenceDao.findByRestaurantId(id);
    }

    @Override
    public List<Review> findByAccountId(int id) {
        return reviewPersistenceDao.findByAccountId(id);
    }
}
