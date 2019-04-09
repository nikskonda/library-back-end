package by.bntu.fitr.service.book;

import by.bntu.firt.NotFoundException;
import by.bntu.fitr.converter.book.AuthorDtoConverter;
import by.bntu.fitr.converter.book.BookDtoConverter;
import by.bntu.fitr.dto.book.AuthorDto;
import by.bntu.fitr.dto.book.BookDto;
import by.bntu.fitr.model.book.Author;
import by.bntu.fitr.model.book.Book;
import by.bntu.fitr.model.book.Genre;
import by.bntu.fitr.model.book.Language;
import by.bntu.fitr.model.book.Organization;
import by.bntu.fitr.model.book.PublishingHouse;
import by.bntu.fitr.repository.book.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

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
        book.setGenres(genreService.getPersistents(book.getGenres()));
        book.setAuthor(authorService.getPersistents(book.getAuthor()));
        book.setTranslator(authorService.getPersistents(book.getTranslator()));
        book.setLanguage(languageService.getPersistents(book.getLanguage()));
        book.setImporter(organizationService.getPersistents(book.getImporter()));
        book.setProducer(organizationService.getPersistents(book.getProducer()));
        book.setPublishingHouse(publishingHouseService.getPersistents(book.getPublishingHouse()));
//        }
        return converter.convertToDto(repository.save(book));
//                .orElseThrow(() -> new ServiceException(String.format(SERVICE_ERROR, "creation", "user"))));
    }


    public BookDto find(Long id) {
        return converter.convertToDto(repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void delete(BookDto bookDto) {
        repository.delete(converter.convertFromDto(bookDto));
    }

}
