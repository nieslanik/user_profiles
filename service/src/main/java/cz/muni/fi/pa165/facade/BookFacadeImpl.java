package cz.muni.fi.pa165.facade;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.dozer.Mapper;

import cz.muni.fi.pa165.dto.BookDTO;
import cz.muni.fi.pa165.dto.CreateBookDTO;
import cz.muni.fi.pa165.entity.Book;
import cz.muni.fi.pa165.enums.BookState;
import cz.muni.fi.pa165.service.BookService;

/**
 * @author Michael Simacek
 *
 */
public class BookFacadeImpl implements BookFacade {

    @Inject
    Mapper mapper;

    @Inject
    BookService service;

    @Override
    public void createBook(CreateBookDTO book) {
        service.create(mapper.map(book, Book.class));
    }

    @Override
    public BookDTO findById(Long id) {
        return mapper.map(service.findById(id), BookDTO.class);
    }

    @Override
    public List<BookDTO> findAll() {
        return service.findAll().stream().map(x -> mapper.map(x, BookDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(Long bookId) {
        service.delete(service.findById(bookId));
    }

    @Override
    public void setState(Long bookId, BookState newState) {
        service.setState(service.findById(bookId), newState);
    }

    @Override
    public List<BookDTO> findByName(String name) {
        return service.findByName(name).stream().map(x -> mapper.map(x, BookDTO.class)).collect(Collectors.toList());
    }

}
