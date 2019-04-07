package by.bntu.fitr.service.news;

import by.bntu.firt.NotFoundException;
import by.bntu.fitr.converter.book.AuthorDtoConverter;
import by.bntu.fitr.converter.news.NewsDtoConverter;
import by.bntu.fitr.dto.book.AuthorDto;
import by.bntu.fitr.dto.news.NewsDto;
import by.bntu.fitr.model.news.News;
import by.bntu.fitr.repository.book.AuthorRepository;
import by.bntu.fitr.repository.news.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class NewsService {

    private static final String NOT_FOUND_ERROR = "exception.not_found.news";

    private NewsRepository repository;
    private NewsDtoConverter converter;

    @Autowired
    public NewsService(NewsRepository newsRepository,
                       NewsDtoConverter newsDtoConverter){
        this.repository = newsRepository;
        this.converter = newsDtoConverter;
    }

    public NewsDto save(NewsDto newsDto){
        News news = converter.convertFromDto(newsDto);
        if (news.getId()!=null && repository.existsById(news.getId())){
            news.setModificationDate(LocalDateTime.now());
        } else {
            news.setCreationDate(LocalDateTime.now());
        }
        return converter.convertToDto(repository.save(news));
//                .orElseThrow(() -> new ServiceException(String.format(SERVICE_ERROR, "creation", "user"))));
    }

    public NewsDto find(Long id){
        return converter.convertToDto(repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
    }


    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void delete(NewsDto newsDto){
        repository.delete(converter.convertFromDto(newsDto));
    }

}
