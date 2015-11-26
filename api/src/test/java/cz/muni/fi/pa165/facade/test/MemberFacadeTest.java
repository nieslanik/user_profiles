/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.facade.test;

import cz.muni.fi.pa165.dto.LoanDTO;
import cz.muni.fi.pa165.dto.MemberDTO;
import cz.muni.fi.pa165.dto.NewLoanDTO;
import cz.muni.fi.pa165.enums.BookState;
import cz.muni.fi.pa165.facade.MemberFacade;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

/**
 *
 * @author xkubist
 */
public class MemberFacadeTest {
   /* @Inject
    MemberFacade facade;
    
    private MemberDTO member1;
    private MemberDTO member2;
    private LoanDTO loan1;
    private LoanDTO loan2;
    @Before
    public void setUp() {
        member1 = new MemberDTO();
        member1.setGivenName("Blanka");
        member1.setSurname("Protrhla");
        member1.setEmail("BerUska15@pokec.sk");
        Date date = new Date(0);
        member1.setRegistrationDate(date);
        member2 = new MemberDTO();
        member2.setGivenName("Petr");
        member2.setSurname("Soustal");
        member2.setEmail("soustal@gmail.com");
        date = new Date(2);
        member2.setRegistrationDate(date);
        facade.createMember(member1);
        facade.createMember(member2);
        
        loan1=new LoanDTO();
        date=new Date(3);
        loan1.setLoanDate(date);
        loan1.setReturnBookState(BookState.NEW);
        date=new Date(5);
        loan1.setReturnDate(date);
        loan1.setReturned(Boolean.TRUE);
        loan2=new LoanDTO();
        date=new Date(3);
        loan2.setLoanDate(date);
        loan2.setReturned(Boolean.FALSE);
//        facade.addNewLoan(loan1);
  //      facade.addNewLoan(loan2);  
    }
    
    @Test
    @Transactional
    public void testCreate() {
        assertNotNull(member1.getId());
        assertNotNull(member2.getId());
    }
    
    @Test
    public void testFindById() {
        assertSame(member1, facade.findById(member1.getId()));
        assertSame(member2, facade.findById(member2.getId()));
        assertNull(facade.findById(Long.MAX_VALUE));
    }
    @Test
    public void testFindAll() {
      
        Set<MemberDTO> members = new HashSet<>();
        members.add(member1);
        members.add(member2);
        Set<MemberDTO> result = new HashSet<>(facade.findAll());
        assertEquals(result, members);
    }
    
    @Test
    public void testDelete() {
        facade.deleteMember(member1.getId());
        assertNull(facade.findById(member1.getId()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteNonexistent() {
        facade.deleteMember(Long.MAX_VALUE);
    }
    
    @Test
    public void testAddNewLoan(){
        assertNotNull(loan1.getMemberId());
        assertNotNull(loan2.getMemberId());
        assertNotNull(loan1.getLoanId());
        assertNotNull(loan2.getLoanId());
    }
    
    public void TestRemoveFromCurrentLoans(){
        facade.removeFromCurrentLoans(loan1.getLoanId());
    }*/
}
