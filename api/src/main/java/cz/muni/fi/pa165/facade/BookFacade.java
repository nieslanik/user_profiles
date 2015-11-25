package cz.muni.fi.pa165.facade;

import java.util.List;

import cz.muni.fi.pa165.dto.BookDTO;
import cz.muni.fi.pa165.dto.CreateBookDTO;
import cz.muni.fi.pa165.enums.BookState;

/**
 * Facade layer for Book entity
 *
 * @author Michael Simacek
 *
 */
public interface BookFacade {
    /**
     * Persists book into database
     *
     * @param book
     *            book to be persisted
     */
    void createBook(CreateBookDTO book);

    /**
     * Returns book with given id or null
     *
     * @param id
     *            book id
     * @return boook or null
     */
    BookDTO findById(Long id);

    /**
     * Finds books by name (there can be multiple copies of the same book)
     *
     * @return list of books
     */
    List<BookDTO> findByName(String name);

    /**
     * Obtains all persisted books
     *
     * @return list of all books
     */
    List<BookDTO> findAll();

    /**
     * Removes book from the database
     *
     * @param book
     */
    void delete(Long bookId);

    /**
     * Sets book current state. Can be set only to a worse (or the same) state
     * than it already was
     *
     * @param bookId
     * @param newState
     */
    void setState(Long bookId, BookState newState);
}
