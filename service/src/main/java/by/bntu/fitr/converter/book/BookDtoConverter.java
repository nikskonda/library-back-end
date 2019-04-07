package by.bntu.fitr.converter.book;

import by.bntu.fitr.converter.AbstractDtoConverter;
import by.bntu.fitr.dto.book.AuthorDto;
import by.bntu.fitr.dto.book.BookDto;
import by.bntu.fitr.model.book.Author;
import by.bntu.fitr.model.book.Book;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookDtoConverter extends AbstractDtoConverter<Book, BookDto> {

    @Autowired
    public BookDtoConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public BookDto convertToDto(Book entity) {
        return modelMapper.map(entity, BookDto.class);
    }

    @Override
    public Book convertFromDto(BookDto dto) {
        return modelMapper.map(dto, Book.class);
    }
}
