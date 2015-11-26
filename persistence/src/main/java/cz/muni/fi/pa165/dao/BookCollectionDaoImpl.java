package cz.muni.fi.pa165.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cz.muni.fi.pa165.entity.BookCollection;

/**
 *
 * @author xkubist
 */
@Repository
public class BookCollectionDaoImpl implements BookCollectionDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(BookCollection b) {
        em.persist(b);
    }

    @Override
    public void delete(BookCollection b) {
        em.remove(b);
    }

    @Override
    public void update(BookCollection b) {
        em.merge(b);
    }

    @Override
    public List<BookCollection> findAll() {
        return em.createQuery("select b from BookCollection b", BookCollection.class).getResultList();
    }

    @Override
    public BookCollection findByName(String name) {
        List<BookCollection> result = em.createQuery("from BookCollection where name = :name", BookCollection.class)
                .setParameter("name", name).getResultList();
        if (result.size() == 0)
            return null;
        else
            return result.get(0);
    }

    @Override
    public BookCollection findById(Long id) {
        return em.find(BookCollection.class, id);
    }
}
