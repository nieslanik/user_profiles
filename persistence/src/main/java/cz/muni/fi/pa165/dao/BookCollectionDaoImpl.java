/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.BookCollection;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author xkubist
 */
@Repository
public class BookCollectionDaoImpl implements BookCollectionDao {
    @PersistenceContext
    private EntityManager em;

    public void create (BookCollection b){
        em.persist(b);
    }
    public void delete(BookCollection b){
        em.remove(b);
    }
    public void update(BookCollection b){
        em.merge(b);
    }
    public List<BookCollection> findAll(){
        return em.createQuery("select b from BookCollection b", BookCollection.class)
				.getResultList();
    }
    public List<BookCollection> findByName(String name){
            return em.createQuery("from BookCollection where name = :name", BookCollection.class).setParameter("name", name).getResultList();
    }
    public BookCollection findById(Long id){
            return em.find(BookCollection.class, id);
    }
}
