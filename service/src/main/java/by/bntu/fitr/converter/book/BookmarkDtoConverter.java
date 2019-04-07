package by.bntu.fitr.converter.book;

import by.bntu.fitr.converter.AbstractDtoConverter;
import by.bntu.fitr.dto.book.AuthorDto;
import by.bntu.fitr.dto.book.BookmarkDto;
import by.bntu.fitr.model.book.Author;
import by.bntu.fitr.model.book.Bookmark;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookmarkDtoConverter extends AbstractDtoConverter<Bookmark, BookmarkDto> {

    @Autowired
    public BookmarkDtoConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public BookmarkDto convertToDto(Bookmark entity) {
        return modelMapper.map(entity, BookmarkDto.class);
    }

    @Override
    public Bookmark convertFromDto(BookmarkDto dto) {
        return modelMapper.map(dto, Bookmark.class);
    }
}
