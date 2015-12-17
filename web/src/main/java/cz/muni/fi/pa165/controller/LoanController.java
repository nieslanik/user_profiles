package cz.muni.fi.pa165.controller;

import cz.muni.fi.pa165.constants.BookStateConstants;
import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.enums.BookState;
import cz.muni.fi.pa165.facade.BookFacade;
import cz.muni.fi.pa165.facade.LoanFacade;
import cz.muni.fi.pa165.facade.MemberFacade;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        MemberDTO member = memberFacade.findByEmail(username);

        List<LoanDTO> loans;
        if (member.isAdmin()) {
            loans = loanFacade.findAll();
        } else {
            loans = memberFacade.getAllLoans(member.getId());
        }

        model.addAttribute("loans", loans);
        return "loan/list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newLoan(@RequestParam(required = false) Long bookId, @RequestParam(required = false) Long memberId, Model model) {
        if (bookId != null) {
            BookDTO book = bookFacade.findById(bookId);
            model.addAttribute("book", book);
        }
        if (memberId != null) {
            MemberDTO member = memberFacade.findById(memberId);
            model.addAttribute("member", member);
        }

        model.addAttribute("createLoan", new CreateLoanDTO());
        return "loan/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createLoan(@ModelAttribute("createLoan") CreateLoanDTO createLoan, Model model,
                             RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (createLoan.getMemberId() == null || createLoan.getBookId() == null) {
            redirectAttributes.addFlashAttribute("alert_warning", "Member or book has not been chosen");
            return "redirect:" + uriBuilder.path("/loans/create").toUriString();
        }

        Long id = loanFacade.createLoan(createLoan);
        redirectAttributes.addFlashAttribute("alert_success", "New loan with id = " + id +  " was created");
        return "redirect:" + uriBuilder.path("/loans/list").toUriString();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteLoan(@PathVariable("id") Long id, RedirectAttributes redirectAttributes,
                             UriComponentsBuilder uriBuilder) {
        try {
            loanFacade.delete(id);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("alert_warning", "Loan doesn't exist");
            return "redirect:" + uriBuilder.path("/loans/list").toUriString();
        }
        redirectAttributes.addFlashAttribute("alert_success", "Loan with id = " + id + " was successfuly deleted");
        return "redirect:" + uriBuilder.path("loans/list").toUriString();
    }

    @RequestMapping(value = "/return/{id}", method = RequestMethod.POST)
    public String returnLoan(@PathVariable("id") Long id, @RequestParam String bookStateStr, Model model,
                             RedirectAttributes redirectAttrs, UriComponentsBuilder uriBuilder) {
        BookState bookState;
        switch (bookStateStr) {
            case BookStateConstants.NEW :
                bookState = BookState.NEW;
                break;
            case BookStateConstants.LIGHT_DAMAGE:
                bookState = BookState.LIGHT_DAMAGE;
                break;
            case BookStateConstants.MEDIUM_DAMAGE:
                bookState = BookState.MEDIUM_DAMAGE;
                break;
            case BookStateConstants.HEAVY_DAMAGE:
                bookState = BookState.HEAVY_DAMAGE;
                break;
            default:
            case BookStateConstants.REMOVED:
                bookState = BookState.REMOVED;
        }
        loanFacade.returnLoan(id, bookState);
        model.addAttribute("loans", loanFacade.findAll());
        redirectAttrs.addFlashAttribute("alert_success", "Loan with id = " + id + " was successfuly returned");
        return "redirect:" + uriBuilder.path("loans/list").toUriString();
    }

    @RequestMapping(value = "/find_book", method = RequestMethod.GET)
    public String findBook(@RequestParam String book, Model model) {
        List<BookDTO> books = bookFacade.findByName(book);
        model.addAttribute("books", books);
        return "loan/show_book_results";
    }

    @RequestMapping(value = "/find_member", method = RequestMethod.GET)
    public String findMember(@RequestParam String member, Model model) {
        List<MemberDTO> members = memberFacade.findByName(member);
        model.addAttribute("members", members);
        return "loan/show_member_results";
    }
}
