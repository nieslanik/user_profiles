/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa036.sprava_uziv_profilov.nosql.persistence;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;
import pa036.sprava_uziv_profilov.nosql.entities.Account;
import sun.security.util.Debug;

/**
 *
 * @author Mato
 */
public class UserPersistence 
{
    private MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
    //private MongoDatabase database = mongoClient.getDatabase("test");
    private DB database = mongoClient.getDB("test");
    public Account create(Account u) 
    {
        JacksonDBCollection<Account, String> coll = JacksonDBCollection.wrap(database.getCollection("Users"), Account.class,
        String.class);
        WriteResult<Account, String> result = coll.insert(u);
        return result.getSavedObject();
    }

    
    public void delete(Account u) 
    {
        JacksonDBCollection<Account, String> coll = JacksonDBCollection.wrap(database.getCollection("Users"), Account.class,
        String.class);
        coll.removeById(u.getId());
    }

    
    public void update(Account u) 
    {
        JacksonDBCollection<Account, String> coll = JacksonDBCollection.wrap(database.getCollection("Users"), Account.class,
        String.class);
        coll.updateById(u.getId(), u);
    }
    
    
    public List<Account> findAll() 
    {
        JacksonDBCollection<Account, String> coll = JacksonDBCollection.wrap(database.getCollection("Users"), Account.class,
        String.class);
        return coll.find().toArray();
    }

    
    public List<Account> findByName(String name) 
    {
        JacksonDBCollection<Account, String> coll = JacksonDBCollection.wrap(database.getCollection("Users"), Account.class,
        String.class);
        return coll.find(DBQuery.is("username", name)).toArray();
    }

    
    public Account findById(String id) 
    {
        JacksonDBCollection<Account, String> coll = JacksonDBCollection.wrap(database.getCollection("Users"), Account.class,
        String.class);
        return coll.findOneById(id);
    }

}
