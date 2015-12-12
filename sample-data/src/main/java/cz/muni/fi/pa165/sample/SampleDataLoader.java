package cz.muni.fi.pa165.sample;

import java.util.Arrays;
import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import cz.muni.fi.pa165.dto.CreateBookCollectionDTO;
import cz.muni.fi.pa165.dto.CreateBookDTO;
import cz.muni.fi.pa165.dto.MemberDTO;
import cz.muni.fi.pa165.dto.MemberRegisterDTO;
import cz.muni.fi.pa165.facade.BookCollectionFacade;
import cz.muni.fi.pa165.facade.BookFacade;
import cz.muni.fi.pa165.facade.MemberFacade;

@Named
public class SampleDataLoader {
    @Inject
    private BookCollectionFacade collectionFacade;

    @Inject
    private BookFacade bookFacade;

    @Inject
    MemberFacade memberFacade;

    public void createSampleData() {
        Long b1 = book("東方文花帖", "射命丸 文", 4758010374L);
        bookCollection("Manga", b1);

        createMember();
        Long b2 = book("kniha", "autor", 12345L);
        bookCollection("collection", b2);
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

    private void createMember() {
        MemberDTO member = new MemberDTO();
        member.setEmail("email@email.com");
        member.setGivenName("peter");
        member.setSurname("cibula");
        member.setRegistrationDate(new Date());

        MemberRegisterDTO memberRegister = new MemberRegisterDTO();
        memberRegister.setMember(member);
        memberRegister.setPassword("12345");
        memberFacade.registerMember(memberRegister);
    }
}
