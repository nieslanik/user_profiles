package cz.muni.fi.pa165.service.test;

import cz.muni.fi.pa165.config.ServiceConfiguration;
import cz.muni.fi.pa165.dao.BookCollectionDao;
import cz.muni.fi.pa165.entity.Book;
import cz.muni.fi.pa165.entity.BookCollection;
import cz.muni.fi.pa165.enums.BookState;
import cz.muni.fi.pa165.service.BookCollectionService;
import cz.muni.fi.pa165.service.BookCollectionServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertSame;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 * Tests for BookCollectionService
 * 
 * @author Jakub Peschel <jakub.peschel@studentagency.cz>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfiguration.class)
public class BookCollectionServiceTest {

    @Mock
    private BookCollectionDao daoMock;

    @InjectMocks
    BookCollectionService service = new BookCollectionServiceImpl();

    private BookCollection collection;
    private Book book;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        book = new Book();
        book.setId(1L);
        book.setName("testBook");
        book.setIsbn(1L);
        book.setState(BookState.MEDIUM_DAMAGE);
        collection = new BookCollection();
        collection.setName("testCollection");
    }

    @Test
    public void testCreate() {
        collection.addBook(book);
        service.create(collection);
        verify(daoMock).create(collection);
    }

    public void testCreateNoBook() {
        service.create(collection);
        verify(daoMock).create(collection);
    }

    @Test
    public void testFindById() {
        when(daoMock.findById(any(Long.class))).thenReturn(collection);
        Long testId = 10l;
        assertSame(collection, service.findById(testId));
    }

    @Test
    public void testFindAll() {
        List<BookCollection> collections = new ArrayList<>();
        collections.add(collection);
        when(daoMock.findAll()).thenReturn(collections);
        assertSame(collections, service.findAll());
    }

    @Test
    public void testDelete() {
        service.delete(collection);
        verify(daoMock).delete(collection);
    }

}
