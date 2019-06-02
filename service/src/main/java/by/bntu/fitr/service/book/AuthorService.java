package by.bntu.fitr.service.book;

import by.bntu.fitr.NotFoundException;
import by.bntu.fitr.converter.book.AuthorDtoConverter;
import by.bntu.fitr.dto.book.AuthorDto;
import by.bntu.fitr.model.book.Author;
import by.bntu.fitr.repository.book.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthorService {

    private static final String NOT_FOUND_ERROR = "exception.notFound.author";

    private AuthorRepository repository;
    private AuthorDtoConverter converter;

    @Autowired
    public AuthorService(AuthorRepository authorRepository,
                         AuthorDtoConverter authorDtoConverter){
        this.repository = authorRepository;
        this.converter = authorDtoConverter;
    }

    public AuthorDto save(AuthorDto authorDto){
        return converter.convertToDto(repository.save(converter.convertFromDto(authorDto)));
//                .orElseThrow(() -> new ServiceException(String.format(SERVICE_ERROR, "creation", "user"))));
    }

    public AuthorDto find(Long id){
        return converter.convertToDto(repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
    }

    public List<AuthorDto> findBySearchString(String searchString){
        return  converter.convertToDtoList(findBySearchStringPersistence(searchString));
    }

    public List<Author> findBySearchStringPersistence(String searchString){
        if (searchString==null){
            searchString="";
        }
        return  repository.findBySearchString('%'+searchString+'%');
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void delete(AuthorDto authorDto){
        repository.delete(converter.convertFromDto(authorDto));
    }

    public Set<Author> getPersistence(Set<Author> authors){
        if (authors == null || authors.isEmpty()) {
            return null;
        }
        Set<Author> persistents = new HashSet<>();
        for (Author author : authors) {
            if (author.getId() != null && repository.existsById(author.getId())) {
                persistents
                        .add(repository
                                .findById(author.getId())
                                .orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
            }
        }
        return persistents;
    }
}
