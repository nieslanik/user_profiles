/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa036.sprava_uziv_profilov.nosql;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;
import pa036.profiles.entity.Account;

/**
 *
 * @author Mato
 */
public class UserPersistence 
{
    private MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
    private MongoDatabase database = mongoClient.getDatabase("test");
    
    public void create(Account u) {
        Document doc = new Document("username", u.getUsername())
               .append("password", u.getPassword())
               .append("ui_lang", u.getUi_lang())
               .append("employeeAccount", u.isEmployee_acount())
               .append("logonStatus", u.getLogon_status())
               .append("logonLastModif", u.getLogon_last_timestamp());
        MongoCollection<Document> c = database.getCollection("Users"); 
        c.insertOne(doc);
    }

    
    /*public void delete(Account u) {
        em.remove(u);
    }

    
    public void update(Account u) {
        em.merge(u);
    }
    
    */
    public MongoCollection<Document> findAll() 
    {
        return database.getCollection("Users");
    }

    
    public Document findByName(String name) 
    {
        MongoCollection<Document> c = database.getCollection("Users");
        return c.find(eq("username", name)).first();
    }

    /*
    public Account findById(Long id) {
        return em.find(Account.class, id);
    }*/
}
