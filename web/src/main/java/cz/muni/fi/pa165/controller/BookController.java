/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 package cz.muni.fi.pa165.controller;




import cz.muni.fi.pa165.dto.BookDTO;
import cz.muni.fi.pa165.dto.CreateBookDTO;
import cz.muni.fi.pa165.enums.BookState;
import cz.muni.fi.pa165.facade.BookFacade;
import java.util.List;
import javax.inject.Inject;
 import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author xkubist
 */
 
@Controller
@RequestMapping("/books")
public class BookController {
    final static Logger logger = LoggerFactory.getLogger(BookController.class);

    @Inject
    private BookFacade bookFacade;

    
    @RequestMapping(value="/all")
    public String getAll(Model model){
        
        logger.debug("rest getBooks");
        model.addAttribute("books",bookFacade.findAll());
        return "book/show_all";
    }
    
    
    
    @RequestMapping(value = "/{id}")
    public String getBook(@PathVariable long id, Model model) throws Exception {
       
        logger.debug("rest getBook({})", id);

        BookDTO book = bookFacade.findById(id);
        if (book == null) {
            return "404";
        }
        model.addAttribute("book",book);
        return "book/show_book";
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createBook(@Valid @ModelAttribute("createBook") CreateBookDTO dto, BindingResult result,
          Model model, UriComponentsBuilder uri){
      if (result.hasErrors()) {
            return "create_book";}
      Long id = bookFacade.createBook(dto);
      return "redirect:" + id; 
      
   }
    
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createBook(Model model){
       model.addAttribute("createBook", new CreateBookDTO());
       return "book/create_book"; 
    }

     @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String changeBookState(@PathVariable long id, String state,
            Model model, UriComponentsBuilder uri){
       System.out.println(""+state);
       bookFacade.setState(id,BookState.valueOf(state));
       return "redirect:/books/" + id; 
    }
     @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String changeState(@PathVariable long id,Model model) {
       BookDTO book = bookFacade.findById(id);
       String state;
        if (book == null) {
            return "404";
        }
       logger.debug("changeState({})", id);
       logger.debug("changeState(book={})", book);
       System.out.println(""+book);

       model.addAttribute("book",book);
       model.addAttribute("state",book.getState());
       return "book/change_state"; 

    }


}
