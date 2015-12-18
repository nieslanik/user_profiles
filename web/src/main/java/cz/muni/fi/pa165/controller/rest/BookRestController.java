package cz.muni.fi.pa165.controller.rest;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import cz.muni.fi.pa165.controller.BookController;
import cz.muni.fi.pa165.dto.BookDTO;
import cz.muni.fi.pa165.dto.CreateBookDTO;
import cz.muni.fi.pa165.enums.BookState;
import cz.muni.fi.pa165.exceptions.LibraryServiceException;
import cz.muni.fi.pa165.exceptions.NotFoundException;
import cz.muni.fi.pa165.facade.BookFacade;

@RestController
@RequestMapping("/rest/books")
public class BookRestController {
    final static Logger logger = LoggerFactory.getLogger(BookController.class);

    @Inject
    private BookFacade bookFacade;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookDTO> getAll() {
        return bookFacade.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BookDTO getBook(@PathVariable long id) {

        logger.debug("rest getBook({})", id);

        BookDTO book = bookFacade.findById(id);
        if (book == null) {
            throw new NotFoundException();
        }
        return book;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createBook(@Valid @RequestBody CreateBookDTO bookDTO, UriComponentsBuilder uri) {
        Long id = bookFacade.createBook(bookDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri.path("/rest/books/{id}").buildAndExpand(id).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}/state", method = RequestMethod.PUT)
    public ResponseEntity<Void> changeBookState(@PathVariable long id, @RequestBody String state) {
        BookDTO dto = bookFacade.findById(id);
        if (dto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            bookFacade.setState(id, BookState.valueOf(state));
        } catch (LibraryServiceException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // response as per
        // http://www.w3.org/Protocols/rfc2616/rfc2616-sec9.html#sec9.6
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteBook(@PathVariable long id) {
        BookDTO dto = bookFacade.findById(id);
        if (dto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bookFacade.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
