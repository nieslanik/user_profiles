package cz.muni.fi.pa165.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontPageController {
    @RequestMapping("/")
    public String frontPage() {
        return "forward:/books/list";
    }
}
