package by.bntu.fitr.converter.book;

import by.bntu.fitr.converter.AbstractDtoConverter;
import by.bntu.fitr.dto.book.AuthorDto;
import by.bntu.fitr.dto.book.GenreDto;
import by.bntu.fitr.model.book.Author;
import by.bntu.fitr.model.book.Genre;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenreDtoConverter extends AbstractDtoConverter<Genre, GenreDto> {

    @Autowired
    public GenreDtoConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public GenreDto convertToDto(Genre entity) {
        return modelMapper.map(entity, GenreDto.class);
    }

    @Override
    public Genre convertFromDto(GenreDto authorDto) {
        return modelMapper.map(authorDto, Genre.class);
    }
}
