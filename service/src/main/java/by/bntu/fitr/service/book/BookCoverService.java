package by.bntu.fitr.service.book;

import by.bntu.fitr.NotFoundException;
import by.bntu.fitr.converter.book.BookCoverDtoConverter;
import by.bntu.fitr.dto.PageableDto;
import by.bntu.fitr.dto.book.BookCoverDto;
import by.bntu.fitr.model.book.BookCover;
import by.bntu.fitr.repository.book.BookCoverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    public Page<BookCoverDto> findByParameters(String searchString, PageableDto pageableDto){
        Pageable pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize(), pageableDto.getDirection(), pageableDto.getSort());
        if (StringUtils.isEmpty(searchString)){
            return  converter.convertToDtoPage(repository.findAll(pageable));
        } else {
            Page<BookCover> page = repository.findBySearchString(searchString, pageable);
            return  converter.convertToDtoPage(page);
        }
    }


}
