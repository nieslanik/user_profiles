package cz.muni.fi.pa165.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.muni.fi.pa165.facade.MemberFacade;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Inject
    MemberFacade userFacade;

    @RequestMapping(method = RequestMethod.GET)
    public String init(Model model) {
        return "login/login";
    }
}
