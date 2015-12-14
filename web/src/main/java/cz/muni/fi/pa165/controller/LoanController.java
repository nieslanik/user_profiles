package cz.muni.fi.pa165.controller;

import cz.muni.fi.pa165.constants.BookStateConstants;
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
import java.util.ArrayList;
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
            redirectAttributes.addFlashAttribute("message", "Member or book has not been chosen");
            return "redirect:/loans/new";
        }

        loanFacade.createLoan(createLoan);
        redirectAttributes.addFlashAttribute("alert_success", "New loan was created");
        return "redirect:" + "/loans/list";
    }

    @RequestMapping(value = "/return/{id}", method = RequestMethod.GET)
    public String returnLoan(@PathVariable("id") Long id, @RequestParam String bookStateStr, Model model) {
        BookState bookState;
        switch (bookStateStr) {
            case BookStateConstants.LIGHT_DAMAGE:
                bookState = BookState.LIGHT_DAMAGE;
                break;
            case BookStateConstants.MEDIUM_DAMAGE:
                bookState = BookState.MEDIUM_DAMAGE;
                break;
            case BookStateConstants.HEAVY_DAMAGE:
                bookState = BookState.HEAVY_DAMAGE;
                break;
            case BookStateConstants.REMOVED:
                bookState = BookState.REMOVED;
                break;
            case BookStateConstants.NEW:
            default:
                bookState = BookState.NEW;
                break;
        }

        loanFacade.returnLoan(id, bookState);
        model.addAttribute("loans", loanFacade.findAll());
        return "loan/list";
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
