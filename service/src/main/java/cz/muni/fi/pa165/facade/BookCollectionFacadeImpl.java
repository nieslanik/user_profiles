/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.BookCollectionDTO;
import cz.muni.fi.pa165.dto.CreateBookCollectionDTO;
import cz.muni.fi.pa165.entity.BookCollection;
import cz.muni.fi.pa165.facade.BookCollectionFacade;
import cz.muni.fi.pa165.service.BookCollectionService;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

/**
 *
 * @author xkubist
 * @email m.kubistel@gmail.com
 */
@Service
public class BookCollectionFacadeImpl implements BookCollectionFacade{
    @Inject
    BookCollectionService service;
    
    @Inject
    Mapper mapper;

    @Override
     public void createBookCollection(CreateBookCollectionDTO bookCollection){
        service.create(mapper.map(bookCollection, BookCollection.class));
    }

    @Override
    public BookCollectionDTO findById(Long id){
        return mapper.map(service.findById(id), BookCollectionDTO.class);
    }
    
    @Override
    public List<BookCollectionDTO> findAll(){
        List<BookCollection> list = service.findAll();
        List<BookCollectionDTO> finalList = new ArrayList<>();
        for (BookCollection bookCollection : list) {
            finalList.add(mapper.map(bookCollection, BookCollectionDTO.class));
        }
        return finalList;
    }

    @Override
    public void delete(Long bookId){
        service.delete(service.findById(bookId));
    }


}