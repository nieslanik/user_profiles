package cz.muni.fi.pa165.controller;

import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.entity.Member;
import cz.muni.fi.pa165.enums.BookState;
import cz.muni.fi.pa165.facade.BookFacade;
import cz.muni.fi.pa165.facade.LoanFacade;
import cz.muni.fi.pa165.facade.MemberFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Juraj Tomko on 12/10/2015.
 */

@Controller
@RequestMapping("/loans")
public class LoanController {

    @Inject
    LoanFacade loanFacade;

    @Inject
    MemberFacade memberFacade;

    @Inject
    BookFacade bookFacade;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listLoans(Model model) {
        List<LoanDTO> loans = loanFacade.findAll();
        model.addAttribute("loans", loans);
        return "loan/list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newLoan(Model model) {
        model.addAttribute("createLoan", new CreateLoanDTO());
        return "loan/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createLoan(@ModelAttribute("createLoan") CreateLoanDTO createLoan, Model model,
                             RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        createTestData();

        loanFacade.createLoan(createLoan);
        redirectAttributes.addFlashAttribute("alert_success", "New loan was created");
        return "redirect:" + "/loans/list";
    }

    @RequestMapping(value = "/return/{id}", method = RequestMethod.GET)
    public String returnLoan(@PathVariable("id") Long id, @RequestParam int bookStateCode, Model model) {
        BookState bookState;
        switch (bookStateCode) {
            case 2: bookState = BookState.LIGHT_DAMAGE; break;
            case 3: bookState = BookState.MEDIUM_DAMAGE; break;
            case 4: bookState = BookState.HEAVY_DAMAGE; break;
            case 5: bookState = BookState.REMOVED; break;
            case 1:
            default:
                bookState = BookState.NEW; break;
        }
        loanFacade.returnLoan(id, bookState);
        model.addAttribute("loans", loanFacade.findAll());
        return "loan/list";
    }

    private void createTestData() {
        CreateBookDTO createBook = new CreateBookDTO();
        createBook.setIsbn(123L);
        createBook.setName("book");
        createBook.setAuthorName("author");
        bookFacade.createBook(createBook);

        MemberDTO member = new MemberDTO();
        member.setEmail("email@email.com");
        member.setGivenName("duri");
        member.setSurname("tomko");
        member.setRegistrationDate(new Date());

        MemberRegisterDTO memberRegister = new MemberRegisterDTO();
        memberRegister.setMember(member);
        memberRegister.setPassword("12345");
        memberFacade.registerMember(memberRegister);
    }
}
