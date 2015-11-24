/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.BookCollectionDao;
import cz.muni.fi.pa165.entity.BookCollection;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author xkubist
 * @email m.kubistel@gmail.com
 */
public class BookCollectionServiceImpl implements BookCollectionService {
    @Inject
    BookCollectionDao bookCollectionDao;
    
    @Override
    public void create(BookCollection b){
        bookCollectionDao.create(b);
    }
    
    @Override
    public void delete(BookCollection b){
        bookCollectionDao.delete(b);
    }
     
    @Override
    public List<BookCollection> findAll(){
        return bookCollectionDao.findAll();
    }
   
    @Override
    public BookCollection findById(Long id){
        return bookCollectionDao.findById(id);
    }
}
