package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.Book;
import cz.muni.fi.pa165.entity.BookCollection;
import java.util.List;

/**
 *
 * @author xkubist
 * @email m.kubistel@gmail.com
 */
public interface BookCollectionService {
     /**
     * Creates the BookCollection in database.
     *
     * @param b BookCollection object to be persisted
     * */
    void create(BookCollection b);
    /**
     * deleteMember the BookCollection from database
     * 
     * @param b BookCollection to be deleted
     */
    void delete(BookCollection b);
    /**
     * update the BookCollection from database
     * 
     * @param b BookCollection to be deleted
     */
    List<BookCollection> findAll();
    /**
     * return BookCollection objects with target name 
     * 
     * @return BookCollection objects with target name 
     */
    BookCollection findById(Long id);
    /**
     * add book to book collection 
     * 
     * @param book 
     */
    void addBookToCollection(Long id,Book book);
    /**
     * remove book from book collection 
     * 
     * @param book 
     */
    void removeBookFromCollection(Long id,Book book);
}
