package by.bntu.fitr.service.book;

import by.bntu.fitr.NotFoundException;
import by.bntu.fitr.converter.book.BookCoverDtoConverter;
import by.bntu.fitr.dto.PageableDto;
import by.bntu.fitr.dto.book.BookCoverDto;
import by.bntu.fitr.dto.book.BookSeatchParameters;
import by.bntu.fitr.model.book.BookCover;
import by.bntu.fitr.model.book.Genre;
import by.bntu.fitr.repository.book.BookCoverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
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

    @Autowired
    public BookCoverService(BookCoverRepository repository, BookCoverDtoConverter converter, GenreService genreService) {
        this.repository = repository;
        this.converter = converter;
        this.genreService = genreService;
    }

    public BookCoverDto find(Long id){
        return converter.convertToDto(repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
    }

    public Page<BookCoverDto> findByParameters(BookSeatchParameters params, PageableDto pageableDto){
        Pageable pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize(), pageableDto.getDirection(), pageableDto.getSort());
        if (!StringUtils.isEmpty(params.getSearchString())){
            Page<BookCover> page = repository.findBySearchString(params.getSearchString(), pageable);
            return  converter.convertToDtoPage(page);

        }
        if (params.getGenres() != null && !params.getGenres().isEmpty()){
            Set<Genre> genreSet = new HashSet<>();
            for (String genre : params.getGenres()){
                genreSet.add(new Genre(genre));
            }
            return converter.convertToDtoPage(repository.findBookCoversByGenres(genreService.getPersistents(genreSet), pageable));
        }
        return  converter.convertToDtoPage(repository.findAll(pageable));
    }


}
