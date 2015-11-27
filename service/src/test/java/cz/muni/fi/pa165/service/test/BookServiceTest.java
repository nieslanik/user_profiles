package cz.muni.fi.pa165.service.test;

import cz.muni.fi.pa165.entity.Book;
import cz.muni.fi.pa165.enums.BookState;
import cz.muni.fi.pa165.exceptions.LibraryServiceException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import cz.muni.fi.pa165.dao.BookDao;
import cz.muni.fi.pa165.service.BookService;
import cz.muni.fi.pa165.service.BookServiceImpl;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juraj Tomko on 11/26/2015.
 */
public class BookServiceTest {

    @Mock
    BookDao daoMock;

    @InjectMocks
    BookService service = new BookServiceImpl();

    private Book book;
    private Book book2;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        book = new Book();
        book.setId(1L);
        book.setName("Hlava 22");
        book.setIsbn(123456l);
        book.setAuthorName("Joseph Heller");
        book.setState(BookState.LIGHT_DAMAGE);
    }

    @Test
    public void testCreate() {
        service.create(book);
        verify(daoMock).create(book);
    }

    @Test
    public void testFindById() {
        when(daoMock.findById(book.getId())).thenReturn(book);
        assertSame(book, service.findById(book.getId()));
    }

    @Test
    public void testFindAll() {

        List<Book> books = new ArrayList<>();
        books.add(book);
        when(daoMock.findAll()).thenReturn(books);
        assertSame(books, service.findAll());
    }

    @Test
    public void testDelete() {
        service.delete(book);
        verify(daoMock).delete(book);
    }

    @Test
    public void testFindByName() {
        List<Book> books = new ArrayList<>();
        when(daoMock.findByName(book.getName())).thenReturn(books);
        assertSame(books, service.findByName(book.getName()));
    }

    @Test
    public void testSetState() {
        when(daoMock.findById(book.getId())).thenReturn(book);
        service.setState(book, BookState.HEAVY_DAMAGE);
        assertEquals(BookState.HEAVY_DAMAGE, service.findById(book.getId()).getState());
    }

    @Test(expected = LibraryServiceException.class)
    public void testSetStateWithLessDamageThenBefore() {
        when(daoMock.findById(book.getId())).thenReturn(book);
        service.setState(book, BookState.NEW);
    }
}
