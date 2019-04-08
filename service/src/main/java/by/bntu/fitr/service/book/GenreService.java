package by.bntu.fitr.service.book;

import by.bntu.firt.NotFoundException;
import by.bntu.fitr.converter.book.AuthorDtoConverter;
import by.bntu.fitr.converter.book.GenreDtoConverter;
import by.bntu.fitr.dto.book.AuthorDto;
import by.bntu.fitr.dto.book.GenreDto;
import by.bntu.fitr.model.book.Language;
import by.bntu.fitr.repository.book.AuthorRepository;
import by.bntu.fitr.repository.book.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

@Service
public class GenreService {

    private static final String NOT_FOUND_ERROR = "exception.not_found.genre";

    private GenreRepository repository;
    private GenreDtoConverter converter;

    @Autowired
    public GenreService(GenreRepository repository, GenreDtoConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public GenreDto save(GenreDto genreDto){
        return converter.convertToDto(repository.save(converter.convertFromDto(genreDto)));
//                .orElseThrow(() -> new ServiceException(String.format(SERVICE_ERROR, "creation", "user"))));
    }

    public GenreDto find(Long id){
        return converter.convertToDto(repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
    }

    public Set<GenreDto> findByParameters(String searchString, Language language){
        if (!StringUtils.isEmpty(searchString)){
            return  converter.convertToDtoSet(repository.findBySearchString(searchString));
        }
        if (language != null){
            return  converter.convertToDtoSet(repository.findByLanguage(language));
        }
        return new HashSet<>();
    }


    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void delete(GenreDto genreDto){
        repository.delete(converter.convertFromDto(genreDto));
    }

}
