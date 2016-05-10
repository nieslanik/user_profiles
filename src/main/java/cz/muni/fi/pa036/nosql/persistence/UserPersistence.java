/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa036.nosql.persistence;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import cz.muni.fi.pa036.nosql.entities.Account;
import cz.muni.fi.pa036.nosql.entities.Restaurant;
import java.util.List;
import org.mongojack.DBQuery;
import org.mongojack.DBUpdate;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mato
 */
@Service
public class UserPersistence
{
    final private MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
    //private MongoDatabase database = mongoClient.getDatabase("test");
    final private DB database = mongoClient.getDB("test");
    
    private JacksonDBCollection<Account, String> coll = JacksonDBCollection.wrap(database.getCollection("Users"), Account.class,
                String.class);
    
    public Account create(Account u)
    {
        /*JacksonDBCollection<Account, String> coll = JacksonDBCollection.wrap(database.getCollection("Users"), Account.class,
                String.class);*/
        WriteResult<Account, String> result = coll.insert(u);
        return result.getSavedObject();
    }


    public void delete(Account u)
    {
        coll.removeById(u.getId());
    }


    public void update(Account u)
    {
        coll.updateById(u.getId(), u);
    }

    /*public void changeLogOnStatus(Account u, int status)
    {
        JacksonDBCollection<Account, String> coll = JacksonDBCollection.wrap(database.getCollection("Users"), Account.class,
                String.class);
        //BasicDBObject o = new BasicDBObject("$set", new BasicDBObject("logon_status", status));
        coll.updateById(u.getId(), DBUpdate.set("logon_status", status));
    }*/

    public List<Account> findAll()
    {
        return coll.find().toArray();
    }


    public Account findByName(String name)
    {
        return coll.findOne(DBQuery.is("username", name));//coll.find(DBQuery.is("username", name)).toArray().get(0);
    }


    public Account findById(String id)
    {
        return coll.findOneById(id);
    }

    public void RemoveAll()
    {
        coll.drop();
    }

}
