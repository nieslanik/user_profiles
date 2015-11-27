package cz.muni.fi.pa165.service;

import java.util.List;

import cz.muni.fi.pa165.entity.Book;
import cz.muni.fi.pa165.enums.BookState;

/**
 * Service layer for Book entity
 *
 * @author Michael Simacek
 *
 */
public interface BookService {
    /**
     * Persists book into database
     *
     * @param book
     *            entity to be persisted
     */
    void create(Book book);

    /**
     * Returns book with given id or nul
     *
     * @param id
     *            book id
     * @return boook or null
     */
    Book findById(Long id);

    /**
     * Obtains all persisted books
     *
     * @return list of all books
     */
    List<Book> findAll();

    /**
     * Finds books by name (there can be multiple copies of the same book)
     *
     * @param name book name to be searched
     * @return list of books
     */
    List<Book> findByName(String name);

    /**
     * Removes book from the database
     *
     * @param book book to be deleted
     */
    void delete(Book book);

    /**
     * Sets book current state. Can be set only to a worse (or the same) state
     * than it already was
     *
     * @param book book to be updated
     * @param newState new state
     */
    void setState(Book book, BookState newState);
}
