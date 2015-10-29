/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.BookCollection;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author xkubist
 */
public class BookCollectionDaoImpl implements BookCollectionDao {
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
    public BookCollection findByName(String name){
            return em.find(BookCollection.class, name);
    }
    public BookCollection findById(Long id){
            return em.find(BookCollection.class, id);
    }
}
