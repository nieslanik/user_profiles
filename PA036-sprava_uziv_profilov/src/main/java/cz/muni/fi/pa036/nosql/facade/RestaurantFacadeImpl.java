/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.nosql.facade;

import cz.muni.fi.pa036.nosq.entities.Restaurant;
import cz.muni.fi.pa036.nosq.entities.Review;
import cz.muni.fi.pa036.nosql.service.AccountService;
import cz.muni.fi.pa036.nosql.service.RestaurantService;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author xnieslan
 */
@Service
@Transactional
public class RestaurantFacadeImpl implements RestaurantFacade{

    @Autowired
    private AccountService accountService;
    
    @Autowired
    private RestaurantService restaurantService;
    
    
    @Override
    public List<Restaurant> topRestaurants() {

        HashMap<Restaurant,Double> map = new HashMap<>();
        ValueComparator bvc =  new ValueComparator(map);
        TreeMap<Restaurant,Double> sorted_map = new TreeMap<>(bvc);

        List<Restaurant> restaurants = restaurantService.findAll();
        for (Restaurant r : restaurants){
            double score = restaurantService.getRating(r.getName());
            
            map.put(r, score);
        }
        
        sorted_map.putAll(map);

        List<Restaurant> result = null;
        for (Restaurant r : sorted_map.keySet()){
            result.add(r);
        }
        
        return result;
    }
    

    @Override
    public int averageScore(String name) {
        return (int) restaurantService.getRating(name);
    }

    @Override
    public List<Review> getReviews(String name) {
        Restaurant r = restaurantService.findById(name);
        return r.getReviews();
    }

    @Override
    public boolean addReview(String description, int score, int restaurantId, int accountId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeReview(int restaurantId, int reviewid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}



class ValueComparator implements Comparator<Restaurant> {

    Map<Restaurant, Double> base;
    public ValueComparator(Map<Restaurant, Double> base) {
        this.base = base;
    }

    // Note: this comparator imposes orderings that are inconsistent with equals.    
    public int compare(Restaurant a, Restaurant b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }
      
}