package cz.muni.fi.pa165.facade;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.dto.BookCollectionDTO;
import cz.muni.fi.pa165.dto.BookDTO;
import cz.muni.fi.pa165.dto.InputBookCollectionDTO;
import cz.muni.fi.pa165.entity.Book;
import cz.muni.fi.pa165.entity.BookCollection;
import cz.muni.fi.pa165.service.BookCollectionService;
import cz.muni.fi.pa165.service.BookService;

/**
 *
 * @author xkubist
 */
@Service
@Transactional
public class BookCollectionFacadeImpl implements BookCollectionFacade {
    @Inject
    private BookCollectionService service;

    @Inject
    private BookService bookService;

    @Inject
    private Mapper mapper;

    @Override
    public Long createBookCollection(InputBookCollectionDTO bookCollection) {
        BookCollection entity = mapper.map(bookCollection, BookCollection.class);
        for (Long id : bookCollection.getBookIds()) {
            entity.addBook(bookService.findById(id));
        }
        service.create(entity);
        return entity.getId();
    }

    @Override
    public BookCollectionDTO findById(Long id) {
        BookCollection entity = service.findById(id);
        if (entity == null)
            return null;
        return mapper.map(entity, BookCollectionDTO.class);
    }

    @Override
    public List<BookCollectionDTO> findAll() {
        List<BookCollection> list = service.findAll();
        List<BookCollectionDTO> finalList = new ArrayList<>();
        for (BookCollection bookCollection : list) {
            finalList.add(mapper.map(bookCollection, BookCollectionDTO.class));
        }
        return finalList;
    }

    @Override
    public void delete(Long bookId) {
        service.delete(service.findById(bookId));
    }

    @Override
    public void addBookToCollection(Long id, BookDTO bookDto) {
        service.addBookToCollection(id, mapper.map(bookDto, Book.class));
    }

    @Override
    public void removeBookFromCollection(Long id, BookDTO bookDto) {
        service.removeBookFromCollection(id, mapper.map(bookDto, Book.class));
    }

    @Override
    public void updateBookCollection(Long id, InputBookCollectionDTO dto) {
        BookCollection collection = service.findById(id);
        // TODO not found
        mapper.map(dto, collection);
        for (Long bookId: dto.getBookIds()) {
            collection.addBook(bookService.findById(bookId));
        }
        service.update(collection);
    }

    @Override
    public InputBookCollectionDTO forUpdate(BookCollectionDTO dto) {
        InputBookCollectionDTO inputDto = mapper.map(dto, InputBookCollectionDTO.class);
        for (BookDTO book: dto.getBooks()) {
            inputDto.getBookIds().add(book.getId());
        }
        return inputDto;
    }
}