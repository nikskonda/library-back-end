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

    private static final String NOT_FOUND_ERROR = "exception.not_found.book";

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
//        if (book.getId()!=null && repository.existsById(book.getId())){
        book.setGenres(genreService.getPersistences(book.getGenres()));
        book.setAuthors(authorService.getPersistences(book.getAuthors()));
        book.setTranslators(authorService.getPersistences(book.getTranslators()));
        book.setLanguage(languageService.getPersistences(bookDto.getLanguage()));
        book.setImporter(organizationService.getPersistences(book.getImporter()));
        book.setProducer(organizationService.getPersistences(book.getProducer()));
        book.setPublishingHouse(publishingHouseService.getPersistences(book.getPublishingHouse()));
        if (book.getYear()==null){
            book.setYear(-1);
        }
        if (book.getRating()==null){
            book.setRating(0);
        }
//        }
        return converter.convertToDto(repository.save(book));
//                .orElseThrow(() -> new ServiceException(String.format(SERVICE_ERROR, "creation", "user"))));
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
