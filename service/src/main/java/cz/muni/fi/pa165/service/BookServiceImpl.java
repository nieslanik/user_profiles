package cz.muni.fi.pa165.service;

import java.util.List;

import javax.inject.Inject;

import cz.muni.fi.pa165.dao.BookDao;
import cz.muni.fi.pa165.entity.Book;
import cz.muni.fi.pa165.enums.BookState;
import cz.muni.fi.pa165.exceptions.LibraryServiceException;

/**
 * Implementation of BookService
 *
 * @author Michael Simacek
 *
 */
public class BookServiceImpl implements BookService {

    @Inject
    BookDao bookDao;

    @Override
    public void create(Book book) {
        bookDao.create(book);
    }

    @Override
    public Book findById(Long id) {
        return bookDao.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public void setState(Book book, BookState newState) {
        if (book.getState().compareTo(newState) < 0) {
            throw new LibraryServiceException("Book cannot be set to less damaged state than it already was");
        }
        book.setState(newState);
        bookDao.update(book);
    }

    @Override
    public void delete(Book book) {
        bookDao.delete(book);
    }
}
