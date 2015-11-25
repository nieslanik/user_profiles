/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service.test;

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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.Set;
/**
 *
 * @author xkubist
 */
public class MemberServiceTest {
    @Inject
    MemberService service;
    
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
        service.register(member1,"totoJeNajneprelomitelnejsieHesloNaSvete");
        service.register(member2, "OhFreddledGruntbugglyThyMicturationsAreToMe");
        
        Book book = new Book();
        book.setName("Clean Code");
        book.setIsbn(1L);
        book.setState(BookState.NEW);
        
        loan1=new Loan();
        loan1.setBook(book);
        loan1.setDate(new Date(1));
        loan1.setReturnBookState(BookState.NEW);
        loan1.setReturnDate(new Date(3));
        loan1.setReturned(Boolean.TRUE);
        
        loan2=new Loan();
        loan2.setBook(new Book());
        loan2.setDate(new Date(10));
        loan2.setReturned(Boolean.FALSE);
        
        loan3=new Loan();
        loan3.setBook(book);
        loan3.setDate(new Date(5));
        loan3.setReturnBookState(BookState.HEAVY_DAMAGE);
        loan3.setReturnDate(new Date(8));
        loan3.setReturned(Boolean.TRUE);
        
        service.addLoanToMember(member1, loan1);
        service.addLoanToMember(member1, loan2);
        service.addLoanToMember(member2, loan3);
        
    }
    
    @Test
    @Transactional/*dokonci registraci s prazdnym heslom a registraciu toho isteho uzivatela*/
    public void testRegister() {
        assertNotNull(member1.getId());
        assertNotNull(member2.getId());
        assertNotNull(member1.getPasswordHash());
        assertNotNull(member1.getPasswordHash());
        //service.register()
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
        service.delete(member1);
        assertNull(service.findById(member1.getId()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteNonexistent() {
        Member myMember = new Member();
        service.delete(myMember);
    }
    
    @Test
    public void testAddLoanToMember() {
       Set<Loan> result = new HashSet<>(service.getAllLoansOfMember(member2));
       assertSame(result.contains(loan3),Boolean.FALSE);
    }

    @Test
    public void testGetAllLoansOfMember(Member member) {
       Set<Loan> loans = new HashSet<>();
        loans.add(loan1);
        loans.add(loan2);
        Set<Loan> result = new HashSet<>(service.getAllLoansOfMember(member1));
        assertEquals(result, loans);
    }
    @Test
    public void testCorrectAuthenticate() {
        assertSame(service.authenticate(member1,"totoJeNajneprelomitelnejsieHesloNaSvete"),Boolean.TRUE);
    }
    
    @Test
    public void testIncorrectAuthenticate() {
        assertSame(service.authenticate(member1,"totoJeNajsieHesloNaSvete"),Boolean.FALSE);
    }
    
    @Test
    public void testEmptyAuthenticate() {
        assertSame(service.authenticate(member1,null),Boolean.FALSE);
    }
    
}
