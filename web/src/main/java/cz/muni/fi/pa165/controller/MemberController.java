package cz.muni.fi.pa165.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.muni.fi.pa165.dto.LoanDTO;
import cz.muni.fi.pa165.dto.MemberDTO;
import cz.muni.fi.pa165.dto.RegisterMemberDTO;
import cz.muni.fi.pa165.exceptions.NotFoundException;
import cz.muni.fi.pa165.facade.MemberFacade;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Inject
    MemberFacade facade;

    @RequestMapping("/{id}")
    public String showMember(@PathVariable long id, Model model) {
        MemberDTO dto = facade.findById(id);
        if (dto == null)
            throw new NotFoundException();
        List<LoanDTO> allLoans = facade.getAllLoans(dto.getId());
        List<LoanDTO> activeLoans = new ArrayList<>();
        List<LoanDTO> returnedLoans = new ArrayList<>();
        for (LoanDTO loan : allLoans) {
            if (loan.getReturned()) {
                returnedLoans.add(loan);
            } else {
                activeLoans.add(loan);
            }
        }
        model.addAttribute("member", dto);
        model.addAttribute("activeloans", activeLoans);
        model.addAttribute("returnedloans", returnedLoans);
        return "member/show";
    }

    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String createMemberView(Model model) {
        model.addAttribute("createMember", new RegisterMemberDTO());
        return "member/create";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createMember(@Valid @ModelAttribute("createMember") RegisterMemberDTO dto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "member/create";
        }
        Long id = facade.registerMember(dto);
        return "redirect:" + id;
    }
}