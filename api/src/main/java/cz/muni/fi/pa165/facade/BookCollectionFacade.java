package cz.muni.fi.pa165.facade;

import java.util.List;

import cz.muni.fi.pa165.dto.BookCollectionDTO;
import cz.muni.fi.pa165.dto.BookDTO;
import cz.muni.fi.pa165.dto.InputBookCollectionDTO;

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
     Long createBookCollection(InputBookCollectionDTO bookCollection);

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
    void removeBookFromCollection(Long id, BookDTO bookDto);

    /**
     * Update book collection and its contents
     * 
     * @param dto object to update collection
     */
    void updateBookCollection(Long id, InputBookCollectionDTO dto);

    /**
     * Converts output DTO (from queries) to input DTO (for update operation)
     * @param dto output DTO
     * @return input DTO
     */
    InputBookCollectionDTO forUpdate(BookCollectionDTO dto);
}
