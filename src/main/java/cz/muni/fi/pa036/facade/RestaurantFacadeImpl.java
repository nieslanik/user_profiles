/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.facade;

import cz.muni.fi.pa036.dto.RestaurantDTO;
import cz.muni.fi.pa036.dto.ReviewDTO;
import cz.muni.fi.pa036.nosql.entities.Restaurant;
import cz.muni.fi.pa036.nosql.entities.Review;
import cz.muni.fi.pa036.nosql.service.AccountService;
import cz.muni.fi.pa036.nosql.service.RestaurantService;

import java.util.*;

import cz.muni.fi.pa036.sql.service.AccountServiceSQL;
import cz.muni.fi.pa036.sql.service.RestaurantServiceSQL;
import cz.muni.fi.pa036.sql.service.ReviewServiceSQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.transform.impl.InterceptFieldCallback;

/**
 *
 * @author xnieslan
 */
public class RestaurantFacadeImpl implements RestaurantFacade{

    @Autowired
    private AccountService accountService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private AccountServiceSQL accountServiceSQL;

    @Autowired
    private RestaurantServiceSQL restaurantServiceSQL;

    @Autowired
    private ReviewServiceSQL reviewServiceSQL;

    public ReviewServiceSQL getReviewServiceSQL() {
        return reviewServiceSQL;
    }

    public void setReviewServiceSQL(ReviewServiceSQL reviewServiceSQL) {
        this.reviewServiceSQL = reviewServiceSQL;
    }

    public AccountServiceSQL getAccountServiceSQL() {
        return accountServiceSQL;
    }

    public void setAccountServiceSQL(AccountServiceSQL accountServiceSQL) {
        this.accountServiceSQL = accountServiceSQL;
    }

    public RestaurantServiceSQL getRestaurantServiceSQL() {
        return restaurantServiceSQL;
    }

    public void setRestaurantServiceSQL(RestaurantServiceSQL restaurantServiceSQL) {
        this.restaurantServiceSQL = restaurantServiceSQL;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public RestaurantService getRestaurantService() {
        return restaurantService;
    }

    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public List<RestaurantDTO> topRestaurants() {

        if (ChooseDB.noSQL) {

            List<Restaurant> restaurants = restaurantService.getTopRestaurants();
            List<RestaurantDTO> restaurantDTOList = new ArrayList<RestaurantDTO>();

            for (Restaurant r : restaurants) {
                RestaurantDTO dto = new RestaurantDTO();
                dto.setId(r.getId());
                dto.setName(r.getName());
                dto.setReviews(getReviews(r.getName()));
                restaurantDTOList.add(dto);

                /*
                for (Review review : r.getReviews()) {
                    ReviewDTO reviewDTO = new ReviewDTO();
                    reviewDTO.setId(review.getId());
                    reviewDTO.setRating(review.getRating());
                    reviewDTO.setRestaurant_id(review.getRestaurant_id());
                    reviewDTO.setText(review.getText());
                    reviewDTO.setUserId(review.getUserId());
                    reviewDTOList.add(reviewDTO);
                }*/

            }

            return restaurantDTOList;

        }else{

            List<cz.muni.fi.pa036.sql.entities.Restaurant> restaurants = restaurantServiceSQL.getTopRestaurants();
            List<RestaurantDTO> restaurantDTOList = new ArrayList<RestaurantDTO>();
            List<ReviewDTO> reviewDTOList = new ArrayList<ReviewDTO>();

            for (cz.muni.fi.pa036.sql.entities.Restaurant r : restaurants) {
                RestaurantDTO dto = new RestaurantDTO();
                dto.setId( Integer.toString(r.getId()) );
                dto.setName(r.getName());
                dto.setReviews(getReviews(r.getName()));
                restaurantDTOList.add(dto);
            }

            return restaurantDTOList;

        }
    }

    @Override
    public int averageScore(String name) {
        if (ChooseDB.noSQL) {
            return (int) restaurantService.getRating(name);
        }else{
            return (int) restaurantServiceSQL.getRating(restaurantServiceSQL.findByName(name).getId());
        }

    }


    @Override
    public List<ReviewDTO> getReviews(String name) {
        if (ChooseDB.noSQL) {
            List<ReviewDTO> reviewDTOList = new ArrayList<ReviewDTO>();

            Restaurant r = restaurantService.findByName(name);

            for (Review review : r.getReviews()) {
                ReviewDTO reviewDTO = new ReviewDTO();
                reviewDTO.setId(review.getId());
                reviewDTO.setRating(review.getRating());
                reviewDTO.setRestaurant_id(review.getRestaurant_id());
                reviewDTO.setText(review.getText());
                reviewDTO.setUserId(review.getUserId());
                reviewDTOList.add(reviewDTO);
            }

            return reviewDTOList;
        }else{

            cz.muni.fi.pa036.sql.entities.Restaurant restaurant = restaurantServiceSQL.findByName(name);
            List<cz.muni.fi.pa036.sql.entities.Review> reviews = reviewServiceSQL.findByRestaurantId(restaurant.getId());

            List<ReviewDTO> reviewDTOList = new ArrayList<ReviewDTO>();

            for (cz.muni.fi.pa036.sql.entities.Review review : reviews){
                ReviewDTO reviewDTO = new ReviewDTO();
                reviewDTO.setId(Integer.toString(review.getId()));
                reviewDTO.setRating(review.getRating());
                reviewDTO.setRestaurant_id(Integer.toString(review.getRestaurant_id()));
                reviewDTO.setText(review.getText());
                reviewDTO.setUserId(Integer.toString(review.getUser_id()));
                reviewDTOList.add(reviewDTO);
            }

            return reviewDTOList;
        }
    }

    @Override
    public boolean addReview(String description, int score, String restaurantId, String accountId) {
        if (ChooseDB.noSQL) {
            if (description != null && score != 0 && restaurantId != null && accountId != null) {

                Review review = new Review();
                review.setRating(score);
                review.setRestaurant_id(restaurantId);
                review.setText(description);
                review.setUserId(accountId);

                restaurantService.addReview(restaurantId, review, accountId);
                return true;
            }
            return false;
        }else{

            if (description != null && score != 0 && restaurantId != null && accountId != null) {

                cz.muni.fi.pa036.sql.entities.Review review = new cz.muni.fi.pa036.sql.entities.Review();
                review.setRating(score);
                review.setRestaurant_id(Integer.parseInt(restaurantId));
                review.setText(description);
                review.setUser_id(Integer.parseInt(accountId));

                reviewServiceSQL.create(review);

                return true;
            }
            return false;
        }
    }
    @Override
    public boolean removeReview(String restaurantId, String reviewId) {
        if (ChooseDB.noSQL) {
            if (restaurantId != null && reviewId != null) {
                String restaurantStringId = restaurantId;
                String reviewStringId = reviewId;

                restaurantService.removeReview(restaurantStringId, reviewStringId);
                return true;
            }
            return false;
        }else {

            if (restaurantId != null && reviewId != null) {
                cz.muni.fi.pa036.sql.entities.Review review =reviewServiceSQL.findById(Integer.parseInt(restaurantId));
                reviewServiceSQL.delete(review);
                return true;
            }
            return false;
        }

    }
}
