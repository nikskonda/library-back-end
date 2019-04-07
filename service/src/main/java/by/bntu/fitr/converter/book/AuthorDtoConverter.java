package by.bntu.fitr.converter.book;

import by.bntu.fitr.converter.AbstractDtoConverter;
import by.bntu.fitr.dto.book.AuthorDto;
import by.bntu.fitr.model.book.Author;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorDtoConverter extends AbstractDtoConverter<Author, AuthorDto> {

    @Autowired
    public AuthorDtoConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public AuthorDto convertToDto(Author entity) {
        return modelMapper.map(entity, AuthorDto.class);
    }

    @Override
    public Author convertFromDto(AuthorDto authorDto) {
        return modelMapper.map(authorDto, Author.class);
    }
}
