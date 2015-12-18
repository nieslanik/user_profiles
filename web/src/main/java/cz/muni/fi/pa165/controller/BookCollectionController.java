package cz.muni.fi.pa165.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.muni.fi.pa165.dto.BookCollectionDTO;
import cz.muni.fi.pa165.dto.InputBookCollectionDTO;
import cz.muni.fi.pa165.exceptions.NotFoundException;
import cz.muni.fi.pa165.facade.BookCollectionFacade;
import cz.muni.fi.pa165.facade.BookFacade;

@Controller
@RequestMapping("/collection")
public class BookCollectionController {

    @Inject
    private BookCollectionFacade collectionFacade;

    @Inject
    private BookFacade bookFacade;

    @RequestMapping("/list")
    public String listCollections(Model model) {
        model.addAttribute("collections", collectionFacade.findAll());
        return "collection/list";
    }

    @RequestMapping("/{id}")
    public String showCollection(@PathVariable long id, Model model) {
        BookCollectionDTO dto = collectionFacade.findById(id);
        if (dto == null)
            throw new NotFoundException();
        model.addAttribute("collection", dto);
        return "collection/show";
    }

    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String createCollectionView(Model model) {
        model.addAttribute("action", "Create");
        if (!model.containsAttribute("collection")) {
            model.addAttribute("collection", new InputBookCollectionDTO());
        }
        model.addAttribute("allBooks", bookFacade.findAll());
        return "collection/create_or_update";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createCollection(@Valid @ModelAttribute("collection") InputBookCollectionDTO dto,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return createCollectionView(model);
        }
        Long id = collectionFacade.createBookCollection(dto);
        return "redirect:" + id;
    }

    @RequestMapping(path = "{id}/update", method = RequestMethod.GET)
    public String updateCollectionView(@PathVariable long id, Model model) {
        model.addAttribute("action", "Modify");
        if (!model.containsAttribute("collection")) {
            model.addAttribute("collection", collectionFacade.forUpdate(collectionFacade.findById(id)));
        }
        model.addAttribute("allBooks", bookFacade.findAll());
        return "collection/create_or_update";
    }

    @RequestMapping(path = "{id}/update", method = RequestMethod.POST)
    public String updateCollection(@PathVariable long id,
            @Valid @ModelAttribute("collection") InputBookCollectionDTO dto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return updateCollectionView(id, model);
        }
        collectionFacade.updateBookCollection(id, dto);
        return "redirect:/collection/" + id;
    }

    @RequestMapping(path = "{id}/delete", method = RequestMethod.POST)
    public String deleteCollection(@PathVariable long id) {
        collectionFacade.delete(id);
        return "redirect:/collection/list";
    }
}