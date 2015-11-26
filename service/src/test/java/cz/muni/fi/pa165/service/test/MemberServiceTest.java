/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service.test;

import cz.muni.fi.pa165.config.ServiceConfiguration;
import cz.muni.fi.pa165.dao.MemberDao;
import cz.muni.fi.pa165.entity.Book;
import cz.muni.fi.pa165.entity.Loan;
import cz.muni.fi.pa165.entity.Member;
import cz.muni.fi.pa165.enums.BookState;
import cz.muni.fi.pa165.service.MemberService;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;
import org.hibernate.service.spi.ServiceException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author xkubist
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfiguration.class)
public class MemberServiceTest {
     @Mock
    private MemberDao productDao;
    
    

    @Autowired
    @InjectMocks
    private MemberService service;

    @BeforeClass
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
    }
    
    private Member member1;
    private Member member2;
    private Loan loan1;
    private Loan loan2;
    private Loan loan3;
    @Before
    public void setUp() {
        member1 = new Member();
        member1.setGivenName("Blanka");
        member1.setSurname("Protrhla");
        member1.setEmail("BerUska15@pokec.sk");
        member1.setIsAdmin(Boolean.TRUE);
        Date date = new Date(0);
        member1.setRegistrationDate(date);
        member2 = new Member();
        member2.setGivenName("Petr");
        member2.setSurname("Soustal");
        member2.setEmail("soustal@gmail.com");
        member2.setIsAdmin(Boolean.FALSE);
        date = new Date(2);
        member2.setRegistrationDate(date);
        service.registerMember(member1, "totoJeNajneprelomitelnejsieHesloNaSvete");
        service.registerMember(member2, "OhFreddledGruntbugglyThyMicturationsAreToMe");

    }
    
    @Test
    @Transactional/*dokonci registraci s prazdnym heslom a registraciu toho isteho uzivatela*/
    public void testRegister() {
        assertNotNull(member1.getId());
        assertNotNull(member2.getId());
        assertNotNull(member1.getPasswordHash());
        assertNotNull(member1.getPasswordHash());
        //service.registerMember()
    }
    
    
    @Test
    public void testFindById() {
        assertSame(member1, service.findById(member1.getId()));
        assertSame(member2, service.findById(member2.getId()));
        assertNull(service.findById(Long.MAX_VALUE));
    }
    @Test
    public void testFindAll() {
      
        Set<Member> members = new HashSet<>();
        members.add(member1);
        members.add(member2);
        Set<Member> result = new HashSet<>(service.findAll());
        assertEquals(result, members);
    }
    
    @Test
    public void testDelete() {
        service.deleteMember(member1);
        assertNull(service.findById(member1.getId()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteNonexistent() {
        Member myMember = new Member();
        service.deleteMember(myMember);
    }
    

    @Test
    public void testGetAllLoansOfMember(Member member) {
       Set<Loan> loans = new HashSet<>();
        loans.add(loan1);
        loans.add(loan2);
        Set<Loan> result = new HashSet<>(service.getAllLoans(member1));
        assertEquals(result, loans);
    }
    @Test
    public void testCorrectAuthenticate() {
        assertSame(service.authenticateMember(member1, "totoJeNajneprelomitelnejsieHesloNaSvete"),Boolean.TRUE);
    }
    
    @Test
    public void testIncorrectAuthenticate() {
        assertSame(service.authenticateMember(member1, "totoJeNajsieHesloNaSvete"),Boolean.FALSE);
    }
    
    @Test
    public void testEmptyAuthenticate() {
        assertSame(service.authenticateMember(member1, null),Boolean.FALSE);
    }
    
}
