package cz.muni.fi.pa165.facade.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cz.muni.fi.pa165.dto.LoanDTO;
import cz.muni.fi.pa165.dto.MemberAuthenticateDTO;
import cz.muni.fi.pa165.dto.MemberDTO;
import cz.muni.fi.pa165.dto.RegisterMemberDTO;
import cz.muni.fi.pa165.entity.Loan;
import cz.muni.fi.pa165.entity.Member;
import cz.muni.fi.pa165.facade.MemberFacade;
import cz.muni.fi.pa165.service.MemberService;

/**
 * Tests for MemberFacade
 * 
 * @author xkubist
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FacadeTestConfiguration.class)
public class MemberFacadeTest {
    
    // mock injected from FacadeTestConfiguration
    
    @Inject
    private MemberService memberServiceMock;

    @Inject
    private MemberFacade facade;
    
    @Test
    public void testCreate() {
        ArgumentCaptor<Member> captorMember = ArgumentCaptor.forClass(Member.class);
        RegisterMemberDTO memreg= new RegisterMemberDTO();
        memreg.setGivenName("a");
        memreg.setPassword("1234");
        facade.registerMember(memreg);
        verify(memberServiceMock).registerMember(captorMember.capture(), eq("1234"));
        assertEquals("a", captorMember.getValue().getGivenName());
    }

    @Test
    public void testFindById() {
        Long l1 = Long.valueOf(1);
        Member member = new Member();
        member.setId(l1);
        when(memberServiceMock.findById(l1)).thenReturn(member);
        MemberDTO dto = facade.findById(l1);
        assertSame(l1, dto.getId());
    }
    
    @Test
    public void testFindAll(){
        Member member = new Member();
        member.setId(1L);
        List<Member> members =new ArrayList<>();
        members.add(member);
        MemberDTO memdto=new MemberDTO();
        memdto.setId(1L);
         List<MemberDTO> membersDto =new ArrayList<>();
        membersDto.add(memdto);
        when(memberServiceMock.findAll()).thenReturn(members);
        assertEquals(membersDto,facade.findAll());
    }
    
    @Test
    public void testDelete() {
        Long l1 = Long.valueOf(1);
        Member member = new Member();
        member.setId(l1);
        when(memberServiceMock.findById(l1)).thenReturn(member);
        facade.deleteMember(l1);
        verify(memberServiceMock).deleteMember(member);
    }
    
    @Test
    public void testGetAllLoansOfMember() {
        Member member = new Member();
        member.setId(1L);
        Loan loan=new Loan();
        loan.setId(2L);
        List<Loan> loans =new ArrayList<>();
        loans.add(loan);
        LoanDTO loandto=new LoanDTO();
        loandto.setId(2L);
        List<LoanDTO> loansDto =new ArrayList<>();
        loansDto.add(loandto);
        when(memberServiceMock.findById(1L)).thenReturn(member);
        when(memberServiceMock.getAllLoans(member)).thenReturn(loans);
        assertEquals(loansDto,facade.getAllLoans(1L));
    }
    
    @Test
    public void testCorrectAuthenticate() {
        String email = "email@email.com";
        Member member = new Member();
        member.setEmail(email);
        MemberAuthenticateDTO memauth = new MemberAuthenticateDTO();
        memauth.setMemberEmail(email);
        memauth.setPassword("OhFreddledGruntbugglyThyMicturationsAreToMe");
        when(memberServiceMock.findByEmail(email)).thenReturn(member);
        when((memberServiceMock.authenticateMember(member, "OhFreddledGruntbugglyThyMicturationsAreToMe"))).thenReturn(Boolean.TRUE);
        assertSame(facade.authenticateMember(memauth),Boolean.TRUE);
    }  
    
    @Test
    public void testIsAdmin() {
        Member member = new Member();
        member.setId(1L);
        member.setIsAdmin(true);
        when(memberServiceMock.findById(1L)).thenReturn(member);
        assertSame(facade.isAdmin(1L),true);
    
    }
}
