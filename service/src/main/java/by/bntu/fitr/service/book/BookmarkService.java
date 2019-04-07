package by.bntu.fitr.service.book;

import by.bntu.firt.NotFoundException;
import by.bntu.fitr.converter.book.AuthorDtoConverter;
import by.bntu.fitr.converter.book.BookmarkDtoConverter;
import by.bntu.fitr.dto.book.AuthorDto;
import by.bntu.fitr.dto.book.BookmarkDto;
import by.bntu.fitr.repository.book.AuthorRepository;
import by.bntu.fitr.repository.book.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BookmarkService {

    private static final String NOT_FOUND_ERROR = "exception.not_found.book";

    private BookmarkRepository repository;
    private BookmarkDtoConverter converter;

    @Autowired
    public BookmarkService(BookmarkRepository repository, BookmarkDtoConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public BookmarkDto save(BookmarkDto bookmarkDto){
        return converter.convertToDto(repository.save(converter.convertFromDto(bookmarkDto)));
//                .orElseThrow(() -> new ServiceException(String.format(SERVICE_ERROR, "creation", "user"))));
    }

    public BookmarkDto find(Long id){
        return converter.convertToDto(repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
    }


    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void delete(BookmarkDto bookmarkDto){
        repository.delete(converter.convertFromDto(bookmarkDto));
    }

}
