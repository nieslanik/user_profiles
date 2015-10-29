/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.dao.test;

import cz.muni.fi.pa165.dao.MemberDao;
import cz.muni.fi.pa165.entity.Member;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author xkubist
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class MemberDaoTest {
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    MemberDao memberDao;
    
    private Member member1;
    private Member member2;
    
    @Before
    public void setUp() {
        member1 = new Member();
        member1.setGivenName("Blanka");
        member1.setSurname("Protrhla");
        member1.setEmail("BerUska15@pokec.sk");
        Date date = new Date(0);
        member1.setRegistrationDate(date);
        member2 = new Member();
        member2.setGivenName("Petr");
        member2.setSurname("Soustal");
        member2.setEmail("soustal@gmail.com");
        date = new Date(2);
        member2.setRegistrationDate(date);
        em.persist(member1);
        em.persist(member2);
        em.flush();
        
    }
    
    @Test
    public void testCreate() {
        memberDao.create(member1);
        memberDao.create(member2);
        assertNotNull(member1.getId());
        assertNotNull(member2.getId());
    }
    
    @Test
    public void testCreateNullName() {
        Member member = new Member();
        memberDao.create(member);
    }
    
    @Test
    public void testUpdate() {
        memberDao.create(member1);
        member1.setGivenName("Janka");
        memberDao.update(member1);
        assertEquals("Janka", em.find(Member.class, member1.getId())
                .getGivenName());
    }
    
    @Test(expected = PersistenceException.class)
    public void testUpdateNonexistent() {
        memberDao.update(member1);
    }
    
    @Test
    public void testFindById() {
        memberDao.create(member1);
        memberDao.create(member2);
        assertSame(member1, memberDao.findById(member1.getId()));
        assertSame(member2, memberDao.findById(member2.getId()));
        assertNull(memberDao.findById(Long.MAX_VALUE));
    }
    @Test
    public void testFindAll() {
        Set<Member> members = new HashSet<>();
        members.add(member1);
        members.add(member2);
        memberDao.create(member1);
        memberDao.create(member2);
        Set<Member> result = new HashSet<>();
        result.addAll(memberDao.findAll());
        assertEquals(result, members);
    }
    
    @Test
    public void testDelete() {
        memberDao.create(member1);
        memberDao.delete(member1);
        assertNull(em.find(Member.class, member1));
    }

    @Test(expected = PersistenceException.class)
    public void testDeleteNonexistent() {
        memberDao.delete(member1);
    }
    
}
