/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa036.sprava_uziv_profilov.nosql;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.sun.org.apache.xml.internal.resolver.helpers.Debug;
import java.net.UnknownHostException;

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
            MongoClient mongoClient = new MongoClient( "localhost" ); 
            DB database = mongoClient.getDB( "myDatabase" ); 
            System.out.println("f");
        } 
        catch (UnknownHostException ex) 
        {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
        }
        
    }
    
}
