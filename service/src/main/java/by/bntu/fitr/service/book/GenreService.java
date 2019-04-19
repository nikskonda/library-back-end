package by.bntu.fitr.service.book;

import by.bntu.fitr.NotFoundException;
import by.bntu.fitr.UnsupportedOperationException;
import by.bntu.fitr.converter.book.GenreDtoConverter;
import by.bntu.fitr.dto.book.GenreDto;
import by.bntu.fitr.dto.book.LanguageDto;
import by.bntu.fitr.model.book.Genre;
import by.bntu.fitr.model.book.Language;
import by.bntu.fitr.repository.book.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GenreService {

    private static final String NOT_FOUND_ERROR = "exception.not_found.genre";
    private static final Integer COUNT_POPULAR_ENTITIES = 20;

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

    public GenreDto find(String name){
        return converter.convertToDto(repository.findByName(name).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
    }

    public Set<GenreDto> findByParameters(String searchString, LanguageDto language){
        return  converter.convertToDtoSet(findByParametersPersistents(searchString, language));
    }

    public Set<Genre> findByParametersPersistents(String searchString, LanguageDto language){
        if ((searchString==null) ||
                (language.getId()==null && StringUtils.isEmpty(language.getTag()))  ){
            throw new UnsupportedOperationException();
        }
        return  repository.findBySearchString('%'+searchString+'%', language.getId(), language.getTag());
    }


    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void delete(GenreDto genreDto){
        repository.delete(converter.convertFromDto(genreDto));
    }

    public Set<GenreDto> getPopularGenres(LanguageDto language){
        if (language.getId()==null && StringUtils.isEmpty(language.getTag())){
            throw new UnsupportedOperationException();
        }

        Set<Genre> genres = new LinkedHashSet<>(
                repository
                    .findByPopularGenresByLang(COUNT_POPULAR_ENTITIES, language.getId(), language.getTag())
                    .stream()
                    .sorted(Comparator.comparing(Genre::getName))
                    .collect(Collectors.toList()));
        return converter.convertToDtoSet(genres);
    }

    public Set<Genre> getPersistents(Set<Genre> genres) {
        if (genres == null || genres.isEmpty()) {
            return null;
        }
        Set<Genre> persistents = new HashSet<>();
        for (Genre genre : genres) {
            System.out.println("Genre Service ="+genre);
            if (genre.getId() != null && repository.existsById(genre.getId())) {
                persistents
                        .add(repository
                                .findById(genre.getId())
                                .orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
            } else if (!StringUtils.isEmpty(genre.getName()) && repository.existsByName(genre.getName())) {
                persistents
                        .add(repository
                                .findByName(genre.getName())
                                .orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
            } else {
                genre.setId(null);
                persistents.add(repository.save(genre));
            }
        }
        return persistents;
    }
}
