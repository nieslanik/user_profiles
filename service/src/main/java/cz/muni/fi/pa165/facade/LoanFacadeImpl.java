package cz.muni.fi.pa165.facade;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.dto.CreateLoanDTO;
import cz.muni.fi.pa165.dto.LoanDTO;
import cz.muni.fi.pa165.entity.Loan;
import cz.muni.fi.pa165.enums.BookState;
import cz.muni.fi.pa165.service.LoanService;

/**
 *
 * @author Jakub Peschel <jakub.peschel@studentagency.cz>
 */
@Service
@Transactional
public class LoanFacadeImpl implements LoanFacade {

    @Inject
    ListMapper mapper;

    @Inject
    LoanService loanService;

    @Override
    public void createLoan(CreateLoanDTO loan) {
        loanService.create(mapper.map(loan, Loan.class));
    }

    @Override
    public LoanDTO findById(Long id) {
        System.out.println(loanService.findById(1L));
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
