package cz.muni.fi.pa165.facade;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.dto.CreateLoanDTO;
import cz.muni.fi.pa165.dto.LoanDTO;
import cz.muni.fi.pa165.entity.Book;
import cz.muni.fi.pa165.entity.Loan;
import cz.muni.fi.pa165.entity.Member;
import cz.muni.fi.pa165.enums.BookState;
import cz.muni.fi.pa165.service.BookService;
import cz.muni.fi.pa165.service.LoanService;
import cz.muni.fi.pa165.service.MemberService;

/**
 *
 * @author Jakub Peschel
 */
@Service
@Transactional
public class LoanFacadeImpl implements LoanFacade {

    @Inject
    private ListMapper mapper;

    @Inject
    private LoanService loanService;
    
    @Inject
    private MemberService memberService;
    
    @Inject
    private BookService bookService;

    @Override
    public Long createLoan(CreateLoanDTO loan) {
        Loan newLoan = mapper.map(loan, Loan.class);
        Member member = memberService.findById(loan.getMemberId());
        newLoan.setMember(member);
        Book book = bookService.findById(loan.getBookId());
        newLoan.setBook(book);
        loanService.create(newLoan);
        return newLoan.getId();
    }

    @Override
    public LoanDTO findById(Long id) {
        return mapper.map(loanService.findById(id), LoanDTO.class);
    }

    @Override
    public List<LoanDTO> findAll() {
        return mapper.map(loanService.findAll(), LoanDTO.class);
    }

    @Override
    public void delete(Long loanId) {
        loanService.delete(loanService.findById(loanId));
    }

    @Override
    public void returnLoan(Long id, BookState returnState) {
        loanService.returnLoan(id, returnState);
    }

}
