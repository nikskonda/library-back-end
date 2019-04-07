package by.bntu.fitr.service.book;

import by.bntu.firt.NotFoundException;
import by.bntu.fitr.converter.book.AuthorDtoConverter;
import by.bntu.fitr.converter.book.BookDtoConverter;
import by.bntu.fitr.dto.book.AuthorDto;
import by.bntu.fitr.dto.book.BookDto;
import by.bntu.fitr.repository.book.AuthorRepository;
import by.bntu.fitr.repository.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BookService {

    private static final String NOT_FOUND_ERROR = "exception.not_found.book";

    private BookRepository repository;
    private BookDtoConverter converter;

    @Autowired
    public BookService(BookRepository repository, BookDtoConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public BookDto save(BookDto bookDto){
        return converter.convertToDto(repository.save(converter.convertFromDto(bookDto)));
//                .orElseThrow(() -> new ServiceException(String.format(SERVICE_ERROR, "creation", "user"))));
    }

    public BookDto find(Long id){
        return converter.convertToDto(repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void delete(BookDto bookDto){
        repository.delete(converter.convertFromDto(bookDto));
    }

}
