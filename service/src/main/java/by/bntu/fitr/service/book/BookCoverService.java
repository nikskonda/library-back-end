package by.bntu.fitr.service.book;

import by.bntu.firt.NotFoundException;
import by.bntu.fitr.converter.book.AuthorDtoConverter;
import by.bntu.fitr.converter.book.BookCoverDtoConverter;
import by.bntu.fitr.dto.book.AuthorDto;
import by.bntu.fitr.dto.book.BookCoverDto;
import by.bntu.fitr.model.book.BookCover;
import by.bntu.fitr.repository.book.AuthorRepository;
import by.bntu.fitr.repository.book.BookCoverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BookCoverService {

    private static final String NOT_FOUND_ERROR = "exception.not_found.book";

    private BookCoverRepository repository;
    private BookCoverDtoConverter converter;

    @Autowired
    public BookCoverService(BookCoverRepository repository, BookCoverDtoConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public BookCoverDto find(Long id){
        return converter.convertToDto(repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
    }

    public Set<BookCoverDto> findBySearchString(String searchString){
        return  converter.convertToDtoSet(repository.findBySearchString(searchString));
    }

}
