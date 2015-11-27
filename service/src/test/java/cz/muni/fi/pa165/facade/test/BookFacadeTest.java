package cz.muni.fi.pa165.facade.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cz.muni.fi.pa165.dto.BookDTO;
import cz.muni.fi.pa165.dto.CreateBookDTO;
import cz.muni.fi.pa165.entity.Book;
import cz.muni.fi.pa165.entity.BookCollection;
import cz.muni.fi.pa165.enums.BookState;
import cz.muni.fi.pa165.facade.BookFacade;
import cz.muni.fi.pa165.service.BookCollectionService;
import cz.muni.fi.pa165.service.BookService;

/**
 * Created by Juraj Tomko on 11/27/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FacadeTestConfiguration.class)
public class BookFacadeTest {

    @Inject
    BookService bookService;

    @Inject
    BookCollectionService collectionService;

    @Inject
    BookFacade facade;

    @Test
    public void testCreate() {
        ArgumentCaptor<Book> captor = ArgumentCaptor.forClass(Book.class);

        CreateBookDTO createBookDto = new CreateBookDTO();
        createBookDto.setName("Hlava 22");
        createBookDto.setAuthorName("Joseph Heller");
        createBookDto.setIsbn(123L);
        createBookDto.addCollectionId(3L);
        BookCollection coll = new BookCollection();
        coll.setId(3L);
        when(collectionService.findById(3L)).thenReturn(coll);

        facade.createBook(createBookDto);
        verify(bookService).create(captor.capture());
        Book entity = captor.getValue();
        assertEquals(createBookDto.getName(), entity.getName());
        assertEquals(createBookDto.getAuthorName(), entity.getAuthorName());
        assertEquals(createBookDto.getIsbn(), entity.getIsbn());
        assertEquals(1, entity.getCollections().size());
        assertEquals(createBookDto.getCollectionIds().get(0), entity.getCollections().iterator().next().getId());
    }

    @Test
    public void testFindById() {
        Long id = new Long(1);
        Book book = new Book();
        book.setId(id);
        when(bookService.findById(book.getId())).thenReturn(book);
        BookDTO bookDTO = facade.findById(id);
        assertEquals(id, bookDTO.getId());
    }

    @Test
    public void testDelete() {
        Long id = new Long(1);
        Book book = new Book();
        book.setId(1l);
        when(bookService.findById(book.getId())).thenReturn(book);
        facade.delete(id);
        verify(bookService).delete(book);
    }

    @Test
    public void testFindAll() {
        Long id1 = new Long(1);
        Long id2 = new Long(2);
        Book book1 = new Book();
        Book book2 = new Book();
        book1.setId(id1);
        book2.setId(id2);
        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        when(bookService.findAll()).thenReturn(books);
        assertEquals(books.size(), facade.findAll().size());
    }

    @Test
    public void testFindByName() {
        Long id = new Long(1);
        Book book = new Book();
        book.setId(id);
        book.setName("Joseph Heller");
        List<Book> books = new ArrayList<>();
        books.add(book);
        when(bookService.findByName(book.getName())).thenReturn(books);
        assertEquals(books.size(), facade.findByName(book.getName()).size());
    }

    @Test
    public void testSetState() {
        Book book = new Book();
        book.setId(3L);
        book.setState(BookState.NEW);
        when(bookService.findById(book.getId())).thenReturn(book);
        facade.setState(book.getId(), BookState.LIGHT_DAMAGE);
        verify(bookService).setState(bookService.findById(book.getId()), BookState.LIGHT_DAMAGE);
    }
}
