package cz.muni.fi.pa165.facade.test;

import cz.muni.fi.pa165.dto.BookDTO;
import cz.muni.fi.pa165.dto.CreateBookDTO;
import cz.muni.fi.pa165.entity.Book;
import cz.muni.fi.pa165.facade.BookFacade;
import cz.muni.fi.pa165.service.BookCollectionService;
import cz.muni.fi.pa165.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

        CreateBookDTO bookDto = new CreateBookDTO();
        bookDto.setName("Hlava 22");
        bookDto.setAuthorName("Joseph Heller");
        bookDto.setIsbn(123l);

        facade.createBook(bookDto);
        verify(bookService).create(captor.capture());
        Book entity = captor.getValue();
        assertEquals(bookDto, entity);
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

}
