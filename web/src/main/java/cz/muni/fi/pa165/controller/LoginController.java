package cz.muni.fi.pa165.controller;

import cz.muni.fi.pa165.dto.MemberAuthenticateDTO;
import cz.muni.fi.pa165.facade.MemberFacade;
import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Inject
    MemberFacade userFacade;

    @RequestMapping(method = RequestMethod.GET)
    public String init(Model model) {
        System.out.println("login init");
        model.addAttribute(
                "member",
                new MemberAuthenticateDTO()
        );
        return "login/login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submit(Model model, @ModelAttribute("loginModel") @Valid MemberAuthenticateDTO memberInfo) {
        System.out.println("login send");
        if (userFacade.authenticateMember(memberInfo)) {
            return "redirect:" + "/";
        } else {
            model.addAttribute("err",
                    "Wrong credentials."
            );
            model.addAttribute(
                    "member",
                    new MemberAuthenticateDTO()
            );
            return "login/login";
        }

    }
}
