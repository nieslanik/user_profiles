package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Book;

import java.util.List;

/**
 * Created by Juraj on 10/29/2015.
 */
public interface BookDao {
    /**
     * Creates persistent representation of book in database.
     * @param book item to be persisted
     */
    void create(Book book);

    /**
     * Updates book item in database.
     * @param book item to be updated
     */
    void update(Book book);

    /**
     * Deletes book item from database.
     * @param book item to be deleted
     */
    void delete(Book book);

    /**
     * Finds list of all books stored in persistent storage.
     * @return list of all books persisted in database.
     */
    List<Book> findAll();

    /**
     * Finds book by id.
     * @param id of desired book.
     * @return found book item.
     */
    Book findById(Long id);
}
