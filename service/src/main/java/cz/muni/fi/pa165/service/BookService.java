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
     * Removes book from the database
     *
     * @param book
     */
    void delete(Book book);

    /**
     * Sets book current state. Can be set only to a worse (or the same) state
     * than it already was
     *
     * @param book
     * @param newState
     */
    void setState(Book book, BookState newState);
}
