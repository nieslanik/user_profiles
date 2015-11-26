package cz.muni.fi.pa165.service.test;

import cz.muni.fi.pa165.config.ServiceConfiguration;
import cz.muni.fi.pa165.dao.BookDao;
import cz.muni.fi.pa165.service.BookService;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

/**
 * Created by Juraj on 11/26/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfiguration.class)
public class BookServiceTest {

    @Mock
    BookDao daoMock;

    @Inject
    @InjectMocks
    BookService service;



}

