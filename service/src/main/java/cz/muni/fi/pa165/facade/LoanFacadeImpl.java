package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.LoanDTO;
import cz.muni.fi.pa165.dto.NewLoanDTO;
import cz.muni.fi.pa165.entity.Loan;
import cz.muni.fi.pa165.enums.BookState;
import cz.muni.fi.pa165.service.LoanService;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.dozer.Mapper;

/**
 *
 * @author Jakub Peschel <jakub.peschel@studentagency.cz>
 */
public class LoanFacadeImpl implements LoanFacade {

    @Inject
    Mapper mapper;

    @Inject
    LoanService loanService;

    @Override
    public void createLoan(NewLoanDTO loan) {
        loanService.create(mapper.map(loan, Loan.class));
    }

    @Override
    public LoanDTO findById(Long id) {
        return mapper.map(loanService.findById(id), LoanDTO.class);
    }

    @Override
    public List<LoanDTO> findAll() {
        return loanService.findAll().stream().map(x -> mapper.map(x, LoanDTO.class)).collect(Collectors.toList());
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
