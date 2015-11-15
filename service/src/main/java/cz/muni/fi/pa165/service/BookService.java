package cz.muni.fi.pa165.service;

import java.util.List;

import cz.muni.fi.pa165.entity.Book;

/**
 * @author Michael Simacek
 *
 */
public interface BookService {
    void createBook(Book book);

    Book findById(Long id);

    List<Book> findAll();
}
