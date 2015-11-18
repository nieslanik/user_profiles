/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.dao;
import cz.muni.fi.pa165.entity.BookCollection;
import java.util.List;

/**
 *
 * @author xkubist
 */
public interface BookCollectionDao {
    /**
     * Creates the BookCollection in database.
     *
     * @param b BookCollection object to be persisted
     * */
    public /**
     * delete the BookCollection from database
     * 
     * @param b BookCollection to be deleted
     */
    void delete(BookCollection b);
    /**
     * update the BookCollection from database
     * 
     * @param b BookCollection to be deleted
     */
    void update(BookCollection b);
    /**
     * return list of all BookCollection objects from database 
     * 
     * @return list of all BookCollection objects from database 
     */
    List<BookCollection> findAll();
    /**
     * return BookCollection objects with target name 
     * 
     * @return BookCollection objects with target name 
     */
    BookCollection findByName(String Name);
    /**
     * return BookCollection objects with target id 
     * 
     * @return BookCollection objects with target id 
     */
    BookCollection findById(Long id);
    
}
