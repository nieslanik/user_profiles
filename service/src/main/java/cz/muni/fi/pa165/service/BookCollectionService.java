package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.Book;
import cz.muni.fi.pa165.entity.BookCollection;
import java.util.List;

/**
 * Service layer for BookCollection entity
 * 
 * @author xkubist
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
     * Update book collectoin
     * @param collection to be updated
     */
    void update(BookCollection b);
    /**
     * update the BookCollection from database
     * 
     * @return list of all book collections
     */
    List<BookCollection> findAll();
    /**
     * return BookCollection object with target name 
     * 
     * @param id collection id
     * @return BookCollection object with target name 
     */
    BookCollection findById(Long id);
    /**
     * add book to book collection 
     * 
     * @param id collection id
     * @param book book object to be added
     */
    void addBookToCollection(Long id,Book book);
    /**
     * remove book from book collection 
     * 
     * @param id collection id
     * @param book book object to be removed
     */
    void removeBookFromCollection(Long id,Book book);
}
