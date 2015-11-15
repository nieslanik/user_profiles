package cz.muni.fi.pa165.service;

import java.util.List;

import javax.inject.Inject;

import cz.muni.fi.pa165.dao.BookDao;
import cz.muni.fi.pa165.entity.Book;

/**
 * @author Michael Simacek
 *
 */
public class BookServiceImpl implements BookService {

    @Inject
    BookDao bookDao;

    @Override
    public void createBook(Book book) {
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

}
