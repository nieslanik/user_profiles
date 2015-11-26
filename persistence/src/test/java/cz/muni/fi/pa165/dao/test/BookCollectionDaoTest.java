package cz.muni.fi.pa165.dao.test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.dao.BookCollectionDao;
import cz.muni.fi.pa165.entity.Book;
import cz.muni.fi.pa165.entity.BookCollection;
import cz.muni.fi.pa165.enums.BookState;
import cz.muni.fi.pa165.spring.LibrarySpringContext;

/**
 * @author Michael Simacek
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = LibrarySpringContext.class)
@Transactional
public class BookCollectionDaoTest {
    @PersistenceContext
    private EntityManager em;

    @Inject
    private BookCollectionDao collectionDao;

    private Book book1;
    private Book book2;
    private Book book3;
    private BookCollection coll1;
    private BookCollection coll2;

    public void prepareData() {
        book1 = new Book();
        book1.setName("Varime s konopim");
        book1.setIsbn(1L);
        book1.setState(BookState.NEW);
        book2 = new Book();
        book2.setName("Kamasutra");
        book2.setIsbn(1L);
        book2.setState(BookState.NEW);
        book3 = new Book();
        book3.setName("Everything I Want to Do Is Illegal");
        book3.setIsbn(1L);
        book3.setState(BookState.NEW);
        em.persist(book1);
        em.persist(book2);
        em.persist(book3);
        em.flush();
        coll1 = new BookCollection();
        coll1.setName("test1");
        coll1.addBook(book1);
        coll1.addBook(book2);
        coll2 = new BookCollection();
        coll2.setName("test2");
        coll2.addBook(book2);
        coll2.addBook(book3);
        collectionDao.create(coll1);
        collectionDao.create(coll2);
        em.flush();
    }

    @Test
    @Transactional
    public void testCreate() {
        prepareData();
        BookCollection c = new BookCollection();
        c.setName("foo");
        collectionDao.create(c);
        assertNotNull(c.getId());
        assertNotNull(coll1.getId());
        assertNotNull(coll2.getId());
    }

    @Test(expected = PersistenceException.class)
    public void testCreateNullName() {
        BookCollection newColl = new BookCollection();
        collectionDao.create(newColl);
    }

    @Test
    @Transactional
    public void testUpdate() {
        prepareData();
        coll2.setName("changed");
        collectionDao.update(coll2);
        assertEquals("changed", em.find(BookCollection.class, coll2.getId()).getName());
    }

    @Test
    public void testFindById() {
        prepareData();
        assertSame(coll1, collectionDao.findById(coll1.getId()));
        assertSame(coll2, collectionDao.findById(coll2.getId()));
        assertNull(collectionDao.findById(666L));
    }

    /*@Test
    public void testFindByName() {
        prepareData();
        assertEquals(coll1, collectionDao.findByName(coll1.getName()));
        assertEquals(coll2, collectionDao.findByName(coll2.getName()));
        assertNull(collectionDao.findByName("nonexistent"));
    }*/

    @Test
    public void testFindAll() {
        prepareData();
        Set<BookCollection> expected = new HashSet<>();
        expected.add(coll1);
        expected.add(coll2);
        Set<BookCollection> actual = new HashSet<>(collectionDao.findAll());
        assertEquals(expected, actual);
    }

    @Test
    public void testDelete() {
        prepareData();
        collectionDao.delete(coll2);
        assertNull(em.find(BookCollection.class, coll2.getId()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteNonexistent() {
        BookCollection newColl = new BookCollection();
        newColl.setId(100L);
        newColl.setName("evil");
        collectionDao.delete(newColl);
    }
}