package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.BookCollectionDTO;
import cz.muni.fi.pa165.dto.BookDTO;
import cz.muni.fi.pa165.dto.CreateBookCollectionDTO;
import cz.muni.fi.pa165.dto.UpdateBookCollectionDTO;

import java.util.List;

/**
 * Facade layer for BookCollection entity
 * 
 * @author xkubist
 */
public interface BookCollectionFacade {
    /**
     * Persists book collection into database
     * @param bookCollection entity to be persisted
     * @return new collection id
     */
     Long createBookCollection(CreateBookCollectionDTO bookCollection);
     
    /**
     * Returns book collection with given id or null
     * @param id collection id
     * @return book collection or null
     */
    BookCollectionDTO findById(Long id);
    
    /**
     * Preturn list of all book collection form database
     * @return book collections from database
     */
    List<BookCollectionDTO> findAll();

    /**
     * delete book collection with given id
     * @param id collection id
     */
    void delete(Long id);
    /**
     * add book to book collection 
     * 
     * @param id collection id
     * @param bookDto DTO object representing book to be added
     */
    void addBookToCollection(Long id,BookDTO bookDto);
    /**
     * remove book from book collection 
     * 
     * @param id collection id
     * @param bookDto DTO object representing book to be removed
     */
    void removeBookFromCollection(Long id,BookDTO bookDto);

    /**
     * Update book collection and its contents
     * 
     * @param dto object to update collection
     */
    void updateBookCollection(UpdateBookCollectionDTO dto);

    /**
     * Finds collection by id for update operation
     * @param id collection id
     * @return update dto for collection
     */
    UpdateBookCollectionDTO findByIdForUpdate(Long id);
}
