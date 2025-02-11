package by.bntu.fitr.service.book;

import by.bntu.fitr.NotFoundException;
import by.bntu.fitr.converter.book.BookCoverDtoConverter;
import by.bntu.fitr.dto.PageableDto;
import by.bntu.fitr.dto.book.BookCoverDto;
import by.bntu.fitr.dto.book.BookSearchParameters;
import by.bntu.fitr.model.book.Author;
import by.bntu.fitr.model.book.Genre;
import by.bntu.fitr.repository.book.BookCoverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class BookCoverService {

    private static final String NOT_FOUND_ERROR = "exception.notFound.book";

    private BookCoverRepository repository;
    private BookCoverDtoConverter converter;
    private GenreService genreService;
    private LanguageService languageService;
    private AuthorService authorService;

    @Autowired
    public BookCoverService(BookCoverRepository repository, BookCoverDtoConverter converter, GenreService genreService, LanguageService languageService, AuthorService authorService) {
        this.repository = repository;
        this.converter = converter;
        this.genreService = genreService;
        this.languageService = languageService;
        this.authorService = authorService;
    }

    public BookCoverDto find(Long id) {
        return converter.convertToDto(repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
    }

    public Page<BookCoverDto> findByParameters(BookSearchParameters params, PageableDto pageableDto) {
        Pageable pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize(), pageableDto.getDirection(), pageableDto.getSort());

        if (params.getGenres() != null && !params.getGenres().isEmpty()) {
            Set<Genre> genreSet = new HashSet<>();
            for (String genreName : params.getGenres()) {
                genreSet.addAll(genreService.findByParametersPersistents(genreName));
//                List<Genre> genrePersistents = (genreService.findByParametersPersistents(genreName));
//                for (Genre genre: genrePersistents){
//                    genreSet.add(genre.getId());
//                }
            }
            return converter.convertToDtoPage(
                    repository
                            .findBookCoversByLanguageTagAndTitleLikeAndGenres(
                                    params.getBookLangTag(),
                                    '%' + params.getSearchString() + '%',
                                    genreSet,
                                    pageable));
        }
        if (params.getAuthors() != null && !params.getAuthors().isEmpty()) {
            Set<Author> authors = new HashSet<>();
            for (String partOfName : params.getAuthors()) {
                authors.addAll(authorService.findBySearchStringPersistence(partOfName));
            }
            return converter.convertToDtoPage(
                    repository
                            .findBookCoversByLanguageTagAndTitleLikeAndAuthors(
                                    params.getBookLangTag(),
                                    '%' + params.getSearchString() + '%',
                                    authors,
                                    pageable));
        }
        return converter
                .convertToDtoPage(repository
                        .findBookCoversByLanguageTagAndTitleLike(
                                params.getBookLangTag(),
                                '%' + params.getSearchString() + '%',
                                pageable)
                    );
    }
}
