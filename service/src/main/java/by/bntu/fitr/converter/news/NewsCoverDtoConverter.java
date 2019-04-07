package by.bntu.fitr.converter.news;

import by.bntu.fitr.converter.AbstractDtoConverter;
import by.bntu.fitr.dto.book.AuthorDto;
import by.bntu.fitr.dto.news.NewsCoverDto;
import by.bntu.fitr.model.book.Author;
import by.bntu.fitr.model.news.NewsCover;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewsCoverDtoConverter extends AbstractDtoConverter<NewsCover, NewsCoverDto> {

    @Autowired
    public NewsCoverDtoConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public NewsCoverDto convertToDto(NewsCover entity) {
        return modelMapper.map(entity, NewsCoverDto.class);
    }

    @Override
    public NewsCover convertFromDto(NewsCoverDto newsCoverDto) {
        return modelMapper.map(newsCoverDto, NewsCover.class);
    }
}
