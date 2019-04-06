package by.bntu.fitr.service.book;

import by.bntu.firt.NotFoundException;
import by.bntu.fitr.converter.AuthorDtoConverter;
import by.bntu.fitr.dto.AuthorDto;
import by.bntu.fitr.repository.book.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthorService {

    private static final String NOT_FOUND_ERROR = "exception.not_found.author";

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

    public Set<AuthorDto> findBySearchString(String searchString){
        return  converter.convertToDtoSet(repository.findBySearchString(searchString));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void delete(AuthorDto authorDto){
        repository.delete(converter.convertFromDto(authorDto));
    }

}
