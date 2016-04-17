/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa036.sprava_uziv_profilov.nosql;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import pa036.profiles.entity.Account;


/**
 *
 * @author Mato
 */
public class MongoDBJDBC 
{
    public void connect()
    {
        try 
        { 
            /*MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
            MongoDatabase database = mongoClient.getDatabase("test");


            System.out.println("Connexion réussie");
            //database.createCollection("Collection");

            Document document = new Document( "name", "Fred").append("age", "28");
            
            MongoCollection<Document> c = database.getCollection("Collection"); 
            c.insertOne(document);
            
            System.out.println("Une nouvelle collection des objets" + c.count());*/
            
            Account u = new Account();
            u.setEmployee_acount(true);
            u.setUsername("1stmato");
            u.setPassword("password");
            u.setUi_lang("français");
            
            UserPersistence p = new UserPersistence();
            p.create(u);
            //System.out.println(p.findAll().find().first());
            System.out.println(p.findByName("1stmato"));
        
        } 
        catch (Exception ex) 
        {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
        }
        
    }
    
}
