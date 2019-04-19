package by.bntu.fitr.service.book;

import by.bntu.fitr.NotFoundException;
import by.bntu.fitr.converter.book.BookCoverDtoConverter;
import by.bntu.fitr.dto.PageableDto;
import by.bntu.fitr.dto.book.*;
import by.bntu.fitr.model.book.Author;
import by.bntu.fitr.model.book.BookCover;
import by.bntu.fitr.model.book.Genre;
import by.bntu.fitr.repository.book.BookCoverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

@Service
public class BookCoverService {

    private static final String NOT_FOUND_ERROR = "exception.not_found.book";

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
            Set<Genre> genres = new HashSet<>();
            for (String genreName : params.getGenres()) {
                Genre genre = new Genre();
                genre.setName(genreName);
                genres.add(genre);
            }
            genres = genreService.getPersistents(genres);
            return converter.convertToDtoPage(
                    repository
                            .findBookCoversByTitleLikeAndLanguageTagAndGenres(
                                    params.getBookLangTag(),
                                    '%' + params.getSearchString() + '%',
                                    genres, pageable));
        }

//        if (params.getGenres()!=null && !params.getGenres().isEmpty()){
//            Set<Genre> genres = new HashSet<>();
//            for (String genreName : params.getGenres()){
//                Genre genre = new Genre();
//                genre.setName(genreName);
//                genres.add(genre);
//            }
//            genres = genreService.getPersistents(genres);
//            repository.findBookCoversByTitleLikeAndLanguageTagAndGenres(params.getBookLangTag(), params.getSearchString(), genres, pageable);
//        }
        Page<BookCover> bc = repository
                .findBookCoversByLanguageTagAndTitleLike(
                        params.getBookLangTag(),
                        '%' + params.getSearchString() + '%',
                        pageable);
        return converter
                .convertToDtoPage(repository
                        .findBookCoversByLanguageTagAndTitleLike(
                                params.getBookLangTag(),
                                '%' + params.getSearchString() + '%',
                                pageable)
                    );
    }

//    public Page<BookCoverDto> findByParameters(BookSearchParameters params, PageableDto pageableDto){
//        Pageable pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize(), pageableDto.getDirection(), pageableDto.getSort());
//
//        LanguageDto languageDto = languageService.findByTag(params.getBookLangTag());
//
//        Set<Genre> genres = new HashSet<>();
//        for (String genreName : params.getGenres()){
//            Genre genre = new Genre();
//            genre.setName(genreName);
//            genres.add(genre);
//        }
//        genres = genreService.getPersistents(genres);
////
////        Set<Author> authors = new HashSet<>();
////        for (String authors : params.getAuthors()){
////            authors.addAll(authorService.findBySearchStringPersistents(authors, languageDto));
////        }
//
//        return converter.convertToDtoPage(repository
//                .findCustom(
////                        languageDto.getTag(),
////                        params.getMinYear(),
////                        params.getMaxYear(),
////                        params.getMinPrice(),
////                        params.getMaxPrice(),
////                        params.getMinRating(),
////                        params.getMaxRating(),
//                        genres,
//                        pageable));
//
////        if (!StringUtils.isEmpty(params.getSearchString())){
////            Page<BookCover> page = repository.findBySearchString(params.getSearchString(), pageable);
////            return  converter.convertToDtoPage(page);
////
////        }
////        if (params.getGenres() != null && !params.getGenres().isEmpty()){
////            Set<Genre> genreSet = new HashSet<>();
////            for (String genre : params.getGenres()){
////                genreSet.add(new Genre(genre));
////            }
////            return converter.convertToDtoPage(repository.findBookCoversByGenres(genreService.getPersistents(genreSet), pageable));
////        }
////        LanguageDto languageDto = new LanguageDto();
////        languageDto.setTag(params.getBookLangTag());
////        return  converter.convertToDtoPage(repository.findBookCoversByLanguageTag(params.getBookLangTag(), pageable));
//    }


}
