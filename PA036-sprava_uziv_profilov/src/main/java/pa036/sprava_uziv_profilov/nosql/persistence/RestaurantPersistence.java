/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa036.sprava_uziv_profilov.nosql.persistence;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import org.bson.types.ObjectId;

import org.mongojack.DBUpdate;
import org.mongojack.JacksonDBCollection;

import org.mongojack.WriteResult;
import pa036.sprava_uziv_profilov.nosql.entities.Account;
import pa036.sprava_uziv_profilov.nosql.entities.Restaurant;
import pa036.sprava_uziv_profilov.nosql.entities.Review;

/**
 *
 * @author Mato
 */
public class RestaurantPersistence 
{    private MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
    //private MongoDatabase database = mongoClient.getDatabase("test");
    private DB database = mongoClient.getDB("test");
    public Restaurant create(Restaurant r) 
    {
        JacksonDBCollection<Restaurant, String> coll = JacksonDBCollection.wrap(database.getCollection("Restaurants"), Restaurant.class,
        String.class);
        WriteResult<Restaurant, String> result = coll.insert(r);
        return result.getSavedObject();
    }
    
    public void AddReview(String restaurantId, Review r)
    {
       JacksonDBCollection<Restaurant, String> coll = JacksonDBCollection.wrap(database.getCollection("Restaurants"), Restaurant.class,
        String.class);
       r.setId(ObjectId.get().toString());
       coll.updateById(restaurantId, DBUpdate.push("reviews", r));
    }
    
    public void RemoveReview(String restaurantId, String reviewId)
    {
       JacksonDBCollection<Restaurant, String> coll = JacksonDBCollection.wrap(database.getCollection("Restaurants"), Restaurant.class,
        String.class);

       //BasicDBObject obj = new BasicDBObject("_id", restaurantId);
       BasicDBObject ids = new BasicDBObject("id", reviewId);
       //BasicDBObject docs = new BasicDBObject("reviews", ids);
       //BasicDBObject pull = new BasicDBObject("$pull", docs);
       WriteResult r =coll.updateById(restaurantId, DBUpdate.pull("reviews", ids));
       //WriteResult r = coll.update(obj, pull);//not finished yet
       System.out.println(r.toString());
    }
    
    
    public Restaurant findById(String id) 
    {
        JacksonDBCollection<Restaurant, String> coll = JacksonDBCollection.wrap(database.getCollection("Restaurants"), Restaurant.class,
        String.class);
        return coll.findOneById(id);
    }
}
