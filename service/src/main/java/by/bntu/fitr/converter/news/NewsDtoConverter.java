package by.bntu.fitr.converter.news;

import by.bntu.fitr.converter.AbstractDtoConverter;
import by.bntu.fitr.dto.book.AuthorDto;
import by.bntu.fitr.dto.news.NewsDto;
import by.bntu.fitr.model.book.Author;
import by.bntu.fitr.model.news.News;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewsDtoConverter extends AbstractDtoConverter<News, NewsDto> {

    @Autowired
    public NewsDtoConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public NewsDto convertToDto(News entity) {
        return modelMapper.map(entity, NewsDto.class);
    }

    @Override
    public News convertFromDto(NewsDto newsDto) {
        return modelMapper.map(newsDto, News.class);
    }
}
