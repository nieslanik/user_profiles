/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.nosql.persistence;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import cz.muni.fi.pa036.nosql.entities.Restaurant;
import cz.muni.fi.pa036.nosql.entities.Review;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.types.ObjectId;
import org.mongojack.*;


/**
 *
 * @author Mato
 */
public class RestaurantPersistence
{
    private MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
    //private MongoDatabase database = mongoClient.getDatabase("test");
    private DB database = mongoClient.getDB("test");

    public Restaurant create(Restaurant r)
    {
        JacksonDBCollection<Restaurant, String> coll = JacksonDBCollection.wrap(database.getCollection("Restaurants"), Restaurant.class,
                String.class);
        WriteResult<Restaurant, String> result = coll.insert(r);
        return result.getSavedObject();
    }

    public void remove(String restaurantId)
    {
        JacksonDBCollection<Restaurant, String> coll = JacksonDBCollection.wrap(database.getCollection("Restaurants"), Restaurant.class,
                String.class);
        coll.removeById(restaurantId);
    }

    public void update(Restaurant r)
    {
        JacksonDBCollection<Restaurant, String> coll = JacksonDBCollection.wrap(database.getCollection("Restaurants"), Restaurant.class,
                String.class);
        coll.updateById(r.getId(), r);
    }

    public Review addReview(String restaurantId, Review r, String userId)
    {
        JacksonDBCollection<Restaurant, String> coll = JacksonDBCollection.wrap(database.getCollection("Restaurants"), Restaurant.class,
                String.class);
        r.setId(ObjectId.get().toString());
        r.setUserId(userId);
        coll.updateById(restaurantId, DBUpdate.push("reviews", r));
        return r;
    }

    public void removeReview(String restaurantId, String reviewId)
    {
        JacksonDBCollection<Restaurant, String> coll = JacksonDBCollection.wrap(database.getCollection("Restaurants"), Restaurant.class,
                String.class);

        //BasicDBObject obj = new BasicDBObject("_id", restaurantId);
        BasicDBObject ids = new BasicDBObject("id", reviewId);
        //BasicDBObject docs = new BasicDBObject("reviews", ids);
        //BasicDBObject pull = new BasicDBObject("$pull", docs);
        WriteResult r = coll.updateById(restaurantId, DBUpdate.pull("reviews", ids));
        //WriteResult r = coll.update(obj, pull);//not finished yet
        System.out.println(r.toString());
    }

    /**
     * Gets average rating of the selected restaurant
     * @param restaurantId
     * @return average rating of the selected restaurant
     */
    public double getRating(String restaurantId)
    {
        /*JacksonDBCollection<Restaurant, String> coll = JacksonDBCollection.wrap(database.getCollection("Restaurants"), Restaurant.class,
        String.class);
        int ratingSum = 0;
        Restaurant r = findById(restaurantId);
        for(Review re : r.getReviews())
        {
            ratingSum += re.getRating();
        }
        return (double) ratingSum / r.getReviews().size();*/


        DBCollection coll = database.getCollection("Restaurants");

        DBObject match = new BasicDBObject("$match", new BasicDBObject("_id", new ObjectId(restaurantId)));
        // create the pipeline operations, first with the $unwind
        DBObject unwind = new BasicDBObject("$unwind", "$reviews");

        // build the $group operations
        DBObject groupFields = new BasicDBObject("_id", restaurantId);
        groupFields.put("avg_rating", new BasicDBObject("$avg", "$reviews.rating"));

        DBObject group = new BasicDBObject("$group", groupFields);
        List<DBObject> pipeline = Arrays.asList(match, unwind, group);

        AggregationOutput output = coll.aggregate(pipeline);
        double r = -1;
        for (DBObject result : output.results()) {
            System.out.println(result);
            r = (double) result.get("avg_rating");
        }
        System.out.println(r);
        return r;

    }

    public List<Restaurant> getTop10()
    {
        //DBCollection coll = database.getCollection("Restaurants");
        JacksonDBCollection<Restaurant, String> coll = JacksonDBCollection.wrap(database.getCollection("Restaurants"), Restaurant.class,
                String.class);
        DBObject unwind = new BasicDBObject("$unwind", "$reviews");

        DBObject project1 = new BasicDBObject("$project", new BasicDBObject("name", 1));

        // build the $group operations
        DBObject groupFields = new BasicDBObject("_id", "$_id");

        groupFields.put("avg_rating", new BasicDBObject("$avg", "$reviews.rating"));

        DBObject project = new BasicDBObject("$project", new BasicDBObject("_id", 1));
        DBObject project2 = new BasicDBObject("$project", new BasicDBObject("avg_rating", 0));
        DBObject project3 = new BasicDBObject("$project", new BasicDBObject("root", "$$ROOT"));
        DBObject group = new BasicDBObject("$group", groupFields);

        DBObject sort = new BasicDBObject("$sort", new BasicDBObject("avg_rating", -1));

        DBObject limit = new BasicDBObject("$limit", 10);
        //List<DBObject> pipeline = Arrays.asList( unwind, group, sort, limit);

        Aggregation<Restaurant> aggregation = new Aggregation<>(Restaurant.class, unwind,  group,  sort, limit, project );
        /*AggregationOutput output = coll.aggregate(pipeline);
        double r = -1;*/
        AggregationResult<Restaurant> result = coll.aggregate(aggregation);

        List<Restaurant> topRestaurants = new ArrayList<Restaurant>();

        for(DBObject r: result.getAggregationOutput().results())
        {
            topRestaurants.add(findById(r.get("_id").toString()));
            System.out.println(r.get("_id"));
        }
        return topRestaurants;//result.results();
        //List<Restaurant> restaurants = result.getAggregationOutput().results();
        //for (DBObject res : result.results()) {
        //    System.out.println(res);
        //r = (double) result.get("avg_rating");
        //}
        //System.out.println(r);
        //return r;
    }

    public Restaurant findById(String id)
    {
        JacksonDBCollection<Restaurant, String> coll = JacksonDBCollection.wrap(database.getCollection("Restaurants"), Restaurant.class,
                String.class);
        return coll.findOneById(id);
    }

    public Restaurant findByName(String name)
    {
        JacksonDBCollection<Restaurant, String> coll = JacksonDBCollection.wrap(database.getCollection("Restaurants"), Restaurant.class,
                String.class);
        return coll.findOne(DBQuery.is("name", name));
    }

    public List<Restaurant> findAll()
    {
        JacksonDBCollection<Restaurant, String> coll = JacksonDBCollection.wrap(database.getCollection("Restaurants"), Restaurant.class,
                String.class);
        return coll.find().toArray();
    }

    public void RemoveAll()
    {
        JacksonDBCollection<Restaurant, String> coll = JacksonDBCollection.wrap(database.getCollection("Restaurants"), Restaurant.class,
                String.class);
        coll.drop();
    }
}
