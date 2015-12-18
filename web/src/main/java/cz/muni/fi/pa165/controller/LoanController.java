package cz.muni.fi.pa165.controller;

import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.enums.BookState;
import cz.muni.fi.pa165.facade.BookFacade;
import cz.muni.fi.pa165.facade.LoanFacade;
import cz.muni.fi.pa165.facade.MemberFacade;
import org.hibernate.annotations.SourceType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import java.util.ArrayList;
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

        model.addAttribute("member", member);
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
    public String returnLoan(@PathVariable("id") Long id, @ModelAttribute BookState bookState, Model model,
                             RedirectAttributes redirectAttrs, UriComponentsBuilder uriBuilder) {
        loanFacade.returnLoan(id, bookState);
        model.addAttribute("loans", loanFacade.findAll());
        redirectAttrs.addFlashAttribute("alert_success", "Loan with id = " + id + " was successfuly returned");
        return "redirect:" + uriBuilder.path("loans/list").toUriString();
    }
    @RequestMapping(value = "/return_view", method = RequestMethod.GET)
    public String returnLoanView(@RequestParam("id") Long id, Model model, RedirectAttributes redirectAtts,
                                 UriComponentsBuilder uriBuilder) {
        LoanDTO loan;
        try {
            loan = loanFacade.findById(id);
        } catch (Exception e) {
            redirectAtts.addFlashAttribute("alert_warning", "Loan with id = " + id + " doesn't exist");
            return "redirect:" + uriBuilder.path("/loans/return_view").toUriString();
        }
        model.addAttribute("loan", loan);
        return "loan/book_return_state";
    }

    @RequestMapping(value = "/find_book", method = RequestMethod.GET)
    public String findBook(@RequestParam String book, Model model) {
        List<BookDTO> allBooks = bookFacade.findAll();

        String regex = "[0-9]+";
        List<BookDTO> foundBooks = new ArrayList<>();
        for (BookDTO item : allBooks) {
            if (item.getName().toLowerCase().contains(book.toLowerCase()) ||
                    item.getAuthorName().toLowerCase().contains(book.toLowerCase()) ||
                    book.toLowerCase().contains(item.getName().toLowerCase()) ||
                    book.toLowerCase().contains(item.getAuthorName().toLowerCase())) {
                foundBooks.add(item);
            }
            if (book.matches(regex)) {
                if (item.getIsbn().equals(Long.valueOf(book))) {
                    foundBooks.add(item);
                }
            }
        }

        model.addAttribute("books", foundBooks);
        return "loan/show_book_results";
    }

    @RequestMapping(value = "/find_member", method = RequestMethod.GET)
    public String findMember(@RequestParam String member, Model model) {
        List<MemberDTO> allMembers = memberFacade.findAll();
        System.out.println("member = " + member);

        List<MemberDTO> foundMembers = new ArrayList<>();
        for (MemberDTO item : allMembers) {
            if (member.toLowerCase().contains(item.getSurname().toLowerCase()) ||
                    member.toLowerCase().contains(item.getGivenName().toLowerCase()) ||
                    item.getSurname().toLowerCase().contains(member.toLowerCase()) ||
                    item.getGivenName().toLowerCase().contains(member.toLowerCase()) ||
                    member.toLowerCase().equals(item.getEmail().toLowerCase())) {
                foundMembers.add(item);
            }
        }

        model.addAttribute("members", foundMembers);
        return "loan/show_member_results";
    }
}
