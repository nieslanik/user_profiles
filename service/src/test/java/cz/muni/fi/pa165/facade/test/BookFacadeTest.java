package cz.muni.fi.pa165.facade.test;

import cz.muni.fi.pa165.facade.BookFacade;
import cz.muni.fi.pa165.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

/**
 * Created by Juraj Tomko on 11/27/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FacadeTestConfiguration.class)
public class BookFacadeTest {

    @Inject
    BookService serviceMock;

    @Inject
    BookFacade facade;

    @Test
    public void testCreate() {

    }
}
