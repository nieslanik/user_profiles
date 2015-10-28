package cz.muni.fi.pa165.dao.test;

import static org.junit.Assert.*;

import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cz.muni.fi.pa165.entity.Book;
import cz.muni.fi.pa165.entity.BookCollection;

/**
 * @author Michael Simacek
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
// TODO @ContextConfiguration(...)
public class BookCollectionDaoTest {
    @PersistenceContext
    private EntityManager em;

    @Inject
    BookCollectionDao collectionDao;

    private Book book1;
    private Book book2;
    private Book book3;
    private BookCollection coll1;
    private BookCollection coll2;

    @Before
    public void setUp() {
        book1 = new Book();
        book1.setName("Varime s konopim");
        book2 = new Book();
        book2.setName("Kamasutra");
        book3 = new Book();
        book3.setName("Everything I Want to Do Is Illegal");
        em.persist(book1);
        em.persist(book2);
        em.persist(book3);
        em.flush();
        coll1 = new BookCollection();
        coll1.setName("test1");
        coll1.addBook(book1);
        coll1.addBook(book2);
        coll2 = new BookCollection();
        coll2.addBook(book2);
        coll2.addBook(book3);
        collectionDao.create(coll1);
        collectionDao.create(coll2);
    }

    @Test
    public void testCreate() {
        assertNotNull(coll1.getId());
        assertNotNull(coll2.getId());
    }

    // @Test(expected = ConstrainValidationException.class)
    @Test(expected = Exception.class)
    public void testCreateNullName() {
        BookCollection newColl = new BookCollection();
        collectionDao.create(newColl);
    }

    @Test
    public void testUpdate() {
        coll2.setName("changed");
        collectionDao.update(coll2);
        assertEquals("changed", em.find(BookCollection.class, coll2.getId())
                .getName());
    }

    @Test(expected = PersistenceException.class)
    public void testUpdateNonexistent() {
        BookCollection newColl = new BookCollection();
        newColl.setId(100);
        newColl.setName("evil");
        collectionDao.update(newColl);
    }

    @Test
    public void testFindById() {
        assertSame(coll1, collectionDao.findById(coll1.getId()));
        assertSame(coll2, collectionDao.findById(coll2.getId()));
        assertNull(collectionDao.findById(666));
    }

    @Test
    public void testFindAll() {
        Set<BookCollection> expected;
        expected.add(coll1);
        expected.add(coll2);
        Set<BookCollection> actual;
        actual.addAll(collectionDao.findAll());
        assertEquals(expected, actual);
    }

    @Test
    public void testDelete() {
        collectionDao.delete(coll2);
        assertNull(em.find(BookCollection.class, coll2));
    }

    @Test(expected = PersistenceException.class)
    public void testDeleteNonexistent() {
        BookCollection newColl = new BookCollection();
        newColl.setId(100);
        newColl.setName("evil");
        collectionDao.delete(newColl);
    }
}