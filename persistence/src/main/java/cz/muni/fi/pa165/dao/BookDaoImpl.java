package cz.muni.fi.pa165.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.entity.Book;

/**
 * Created by Juraj Tomko on 10/29/2015.
 */
@Repository
@Transactional
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Book book) {
        em.persist(book);
    }

    @Override
    public void update(Book book) {
        em.merge(book);
    }

    @Override
    public void delete(Book book) {
        em.remove(findById(book.getId()));
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("Select b from Book b", Book.class);
        return query.getResultList();
    }

    @Override
    public Book findById(Long id) {
        return em.find(Book.class, id);
    }
}
