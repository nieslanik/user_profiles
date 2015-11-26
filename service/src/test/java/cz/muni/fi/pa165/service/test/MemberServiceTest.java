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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
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
    MemberDao memberMock;

    @Inject
    @InjectMocks
    MemberService service;
    
    private Member member;
    @Before
    public void setUp() {
         MockitoAnnotations.initMocks(this);
        member = new Member();
        member.setGivenName("Blanka");
        member.setSurname("Protrhla");
        member.setEmail("BerUska15@pokec.sk");
        member.setIsAdmin(Boolean.TRUE);
        Date date = new Date(0);
        member.setRegistrationDate(date);       
    }
    
    @Test
    @Transactional/*dokonci registraci s prazdnym heslom a registraciu toho isteho uzivatela*/
    public void testRegister() {
        service.registerMember(member, "OhFreddledGruntbugglyThyMicturationsAreToMe");
        verify(memberMock).create(member);
        assertNotNull(member.getId());
    }
    
    
    @Test
    public void testFindById() {
        when(memberMock.findById(member.getId())).thenReturn(member);
        assertSame(member, service.findById(member.getId()));
    }
    @Test
    public void testFindAll() {
      
        List<Member> members = new ArrayList<>();
        members.add(member);
         when(memberMock.findAll()).thenReturn(members);
        assertEquals(members, service.findAll());
    }
    
    @Test
    public void testDelete() {
        service.deleteMember(member);
        verify(memberMock).delete(member);
    }

   
    
   
    @Test
    public void testGetAllLoansOfMember() {
        
        Book book = new Book();
        book.setName("Clean Code");
        book.setIsbn(1L);
        book.setState(BookState.NEW);
        
        Loan loan1=new Loan();
        loan1.setBook(book);
        loan1.setDate(new Date(1));
        loan1.setReturnBookState(BookState.NEW);
        loan1.setReturnDate(new Date(3));
        loan1.setReturned(Boolean.TRUE);
        
        Loan loan2=new Loan();
        loan2.setBook(new Book());
        loan2.setDate(new Date(10));
        loan2.setReturned(Boolean.FALSE);
        
        List<Loan> loans = new ArrayList<>();
        loans.add(loan1);
        loans.add(loan2);
        member.addLoan(loan1);
        member.addLoan(loan2);
        
        when(memberMock.findById(member.getId())).thenReturn(member);
        assertSame(loans, service.getAllLoans(member));
    }
    @Test
    public void testCorrectAuthenticate() {
        assertSame(service.authenticateMember(member, "totoJeNajneprelomitelnejsieHesloNaSvete"),Boolean.TRUE);
    }
    
    @Test
    public void testIncorrectAuthenticate() {
        assertSame(service.authenticateMember(member, "totoJeNajsieHesloNaSvete"),Boolean.FALSE);
    }
    
    @Test
    public void testEmptyAuthenticate() {
        assertSame(service.authenticateMember(member, null),Boolean.FALSE);
    }
    
}
