package cz.muni.fi.pa165.sample;

import java.util.Arrays;

import javax.inject.Inject;
import javax.inject.Named;

import cz.muni.fi.pa165.dto.CreateBookCollectionDTO;
import cz.muni.fi.pa165.dto.CreateBookDTO;
import cz.muni.fi.pa165.facade.BookCollectionFacade;
import cz.muni.fi.pa165.facade.BookFacade;

@Named
public class SampleDataLoader {
    @Inject
    private BookCollectionFacade collectionFacade;

    @Inject
    private BookFacade bookFacade;

    public void createSampleData() {
        Long b1 = book("東方文花帖", "射命丸 文", 4758010374L);
        bookCollection("Manga", b1);
    }

    private Long book(String name, String authorName, Long isbn) {
        CreateBookDTO dto = new CreateBookDTO();
        dto.setName(name);
        dto.setAuthorName(authorName);
        dto.setIsbn(isbn);
        return bookFacade.createBook(dto);
    }

    private Long bookCollection(String name, Long... ids) {
        CreateBookCollectionDTO dto = new CreateBookCollectionDTO();
        dto.setName(name);
        dto.setBookIds(Arrays.asList(ids));
        return collectionFacade.createBookCollection(dto);
    }
}
