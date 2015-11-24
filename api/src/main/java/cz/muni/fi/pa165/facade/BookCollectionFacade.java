/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.BookCollectionDTO;
import cz.muni.fi.pa165.dto.CreateBookCollectionDTO;
import java.util.List;

/**
 *
 * @author xkubist
 */
public interface BookCollectionFacade {
    /**
     * Persists book collection into database
     * @param book collection to be persisted
     */
     void createBookCollection(CreateBookCollectionDTO bookCollection);
     
    /**
     * Returns book collection with given id or null
     * @param id
     * @return boook collection or null
     */
    BookCollectionDTO findById(Long id);
    
    /**
     * Preturn list of all book collection form database
     * @return boook collections from database
     */
    List<BookCollectionDTO> findAll();

    /**
     * delete book collection with given id
     * @param id
     */
    void delete(Long bookId);

    
}
