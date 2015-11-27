package cz.muni.fi.pa165.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import cz.muni.fi.pa165.dao.BookCollectionDao;
import cz.muni.fi.pa165.entity.Book;
import cz.muni.fi.pa165.entity.BookCollection;

/**
 *
 * @author xkubist
 */
@Service
public class BookCollectionServiceImpl implements BookCollectionService {

    @Inject
    private BookCollectionDao bookCollectionDao;

    @Override
    public void create(BookCollection b) {
        bookCollectionDao.create(b);
    }

    @Override
    public void delete(BookCollection b) {
        bookCollectionDao.delete(b);
    }

    @Override
    public List<BookCollection> findAll() {
        return bookCollectionDao.findAll();
    }

    @Override
    public BookCollection findById(Long id) {
        return bookCollectionDao.findById(id);
    }

    @Override
    public void addBookToCollection(Long id, Book book) {
        BookCollection coll = bookCollectionDao.findById(id);
        coll.addBook(book);
        bookCollectionDao.update(coll);

    }

    @Override
    public void removeBookFromCollection(Long id, Book book) {
        BookCollection coll = bookCollectionDao.findById(id);
        coll.removeBook(book);
        bookCollectionDao.update(coll);
    }
}
