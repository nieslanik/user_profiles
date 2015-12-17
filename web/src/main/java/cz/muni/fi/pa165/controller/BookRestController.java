/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 package cz.muni.fi.pa165.controller;




import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import cz.muni.fi.pa165.dto.BookDTO;
import cz.muni.fi.pa165.dto.CreateBookDTO;
import cz.muni.fi.pa165.enums.BookState;
import cz.muni.fi.pa165.facade.BookFacade;


import java.util.List;
import java.util.logging.Level;
import javax.inject.Inject;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;


@RestController
@RequestMapping("/rest/books")
public class BookRestController {
    final static Logger logger = LoggerFactory.getLogger(BookController.class);

    @Inject
    private BookFacade bookFacade;

    
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String getAll(){
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = "";
        try {
            json = ow.writeValueAsString(bookFacade.findAll());
        } catch (JsonProcessingException ex) {
            java.util.logging.Logger.getLogger(BookRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
    
    
    
    @RequestMapping(value = "/{id}")
    public BookDTO getBook(@PathVariable long id) throws Exception {
       
        logger.debug("rest getBook({})", id);

        BookDTO book = bookFacade.findById(id);
        if (book == null) {
            throw new IllegalArgumentException("Book not found!");
        }
        return book;
    }
    
  
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Long createBook(@RequestBody CreateBookDTO bookDTO){
       Long id = bookFacade.createBook(bookDTO);
       return id;
    }

     @RequestMapping(value = "/update/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void changeBookState(@PathVariable long id, String state){
       System.out.println(""+state);
       bookFacade.setState(id,BookState.valueOf(state)); 
    }
    
}
