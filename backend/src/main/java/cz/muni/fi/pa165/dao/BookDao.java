package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Book;

import java.util.List;

/**
 * Created by Juraj on 10/29/2015.
 */
public interface BookDao {
    void create(Book book);
    void update(Book book) throws IllegalArgumentException;
    void delete(Book book) throws IllegalArgumentException;
    List<Book> findAll();

    Book findById(Long id);
}
