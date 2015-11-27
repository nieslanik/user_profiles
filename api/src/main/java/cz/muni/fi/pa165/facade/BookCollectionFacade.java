package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.BookCollectionDTO;
import cz.muni.fi.pa165.dto.BookDTO;
import cz.muni.fi.pa165.dto.CreateBookCollectionDTO;
import java.util.List;

/**
 *
 * @author xkubist
 */
public interface BookCollectionFacade {
    /**
     * Persists book collection into database
     * @param bookCollection
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
    /**
     * add book to book collection 
     * 
     * @param bookDto
     */
    void addBookToCollection(Long id,BookDTO bookDto);
    /**
     * remove book from book collection 
     * 
     * @param bookDto 
     */
    void removeBookFromCollection(Long id,BookDTO bookDto);
    
}
