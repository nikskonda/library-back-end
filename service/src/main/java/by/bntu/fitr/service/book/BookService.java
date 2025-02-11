package by.bntu.fitr.service.book;

import by.bntu.fitr.NotFoundException;
import by.bntu.fitr.converter.book.BookDtoConverter;
import by.bntu.fitr.dto.book.BookDto;
import by.bntu.fitr.model.book.Book;
import by.bntu.fitr.repository.book.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private static final String NOT_FOUND_ERROR = "exception.notFound.book";

    private BookRepository repository;
    private BookDtoConverter converter;
    private GenreService genreService;
    private AuthorService authorService;
    private LanguageService languageService;
    private OrganizationService organizationService;
    private PublishingHouseService publishingHouseService;

    @Autowired
    public BookService(BookRepository repository,
                       BookDtoConverter converter,
                       GenreService genreService,
                       AuthorService authorService,
                       LanguageService languageService,
                       OrganizationService organizationService,
                       PublishingHouseService publishingHouseService) {
        this.repository = repository;
        this.converter = converter;
        this.genreService = genreService;
        this.authorService = authorService;
        this.languageService = languageService;
        this.organizationService = organizationService;
        this.publishingHouseService = publishingHouseService;
    }

    public BookDto save(BookDto bookDto) {
        Book book = converter.convertFromDto(bookDto);
        book.setTitle(book.getTitle().toUpperCase());
//        if (book.getId()!=null && repository.existsById(book.getId())){
        book.setGenres(genreService.getPersistences(book.getGenres()));
        book.setAuthors(authorService.getPersistence(book.getAuthors()));
        book.setTranslators(authorService.getPersistence(book.getTranslators()));
        book.setLanguage(languageService.getPersistence(bookDto.getLanguage()));
        book.setImporter(organizationService.getPersistence(book.getImporter()));
        book.setProducer(organizationService.getPersistence(book.getProducer()));
        book.setPublishingHouse(publishingHouseService.getPersistences(book.getPublishingHouse()));
        if (book.getRating()==null){
            book.setRating(0);
        }
        if (book.getInLibraryUseOnly()==null){
            book.setInLibraryUseOnly(false);
        }
//        if (book.getCount()==null){
//            book.setCount(1);
//        }
        return converter.convertToDto(repository.save(book));
    }


    public BookDto find(Long id) {
        return converter.convertToDto(getPersistence(id));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void delete(BookDto bookDto) {
        repository.delete(converter.convertFromDto(bookDto));
    }

    public Book getPersistence(Long id){
        return repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR));
    }

}
