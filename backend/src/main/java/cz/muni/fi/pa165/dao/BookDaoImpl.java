package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Juraj on 10/29/2015.
 */
@Repository
@Transactional
public class BookDaoImpl implements BookDao {

    @PersistenceUnit
    private EntityManager em;

    @Override
    public void create(Book book) {
        em.persist(book);
    }

    @Override
    public void update(Book book) throws IllegalArgumentException {
        em.merge(book);
    }

    @Override
    public void delete(Book book) throws IllegalArgumentException {
        em.remove(findBookById(book.getId()));
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("Select b from Book b", Book.class);
        return query.getResultList();
    }

    @Override
    public Book findBookById(Long id) {
        return em.find(Book.class, id);
    }
}
