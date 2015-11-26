package cz.muni.fi.pa165.service.test;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import cz.muni.fi.pa165.dao.BookDao;
import cz.muni.fi.pa165.service.BookService;
import cz.muni.fi.pa165.service.BookServiceImpl;

/**
 * Created by Juraj on 11/26/2015.
 */
public class BookServiceTest {

    @Mock
    BookDao daoMock;

    @InjectMocks
    BookService service = new BookServiceImpl();

}
