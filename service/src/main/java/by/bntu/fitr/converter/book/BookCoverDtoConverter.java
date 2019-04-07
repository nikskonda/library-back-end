package by.bntu.fitr.converter.book;

import by.bntu.fitr.converter.AbstractDtoConverter;
import by.bntu.fitr.dto.book.AuthorDto;
import by.bntu.fitr.dto.book.BookCoverDto;
import by.bntu.fitr.model.book.Author;
import by.bntu.fitr.model.book.BookCover;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookCoverDtoConverter extends AbstractDtoConverter<BookCover, BookCoverDto> {

    @Autowired
    public BookCoverDtoConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public BookCoverDto convertToDto(BookCover entity) {
        return modelMapper.map(entity, BookCoverDto.class);
    }

    @Override
    public BookCover convertFromDto(BookCoverDto dto) {
        return modelMapper.map(dto, BookCover.class);
    }
}
