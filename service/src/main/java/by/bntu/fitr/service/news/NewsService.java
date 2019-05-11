package by.bntu.fitr.service.news;

import by.bntu.fitr.NotFoundException;
import by.bntu.fitr.converter.news.NewsDtoConverter;
import by.bntu.fitr.dto.news.NewsDto;
import by.bntu.fitr.model.news.News;
import by.bntu.fitr.repository.news.NewsRepository;
import by.bntu.fitr.service.book.LanguageService;
import by.bntu.fitr.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NewsService {

    private static final String NOT_FOUND_ERROR = "exception.not_found.news";

    private NewsRepository repository;
    private NewsDtoConverter converter;
    private UserService userService;
    private LanguageService languageService;

    @Autowired
    public NewsService(NewsRepository repository, NewsDtoConverter converter, UserService userService, LanguageService languageService) {
        this.repository = repository;
        this.converter = converter;
        this.userService = userService;
        this.languageService = languageService;
    }

    public NewsDto save(NewsDto newsDto, String username){
        News news = converter.convertFromDto(newsDto);
        if (news.getId()!=null && repository.existsById(news.getId())){
            news.setLanguage(languageService.getPersistences(newsDto.getLanguage()));
            news.setModificationDate(LocalDateTime.now());
            news.setCreationDate(find(news.getId()).getCreationDate());
        } else {
            news.setCreationDate(LocalDateTime.now());
        }
        news.setCreator(userService.getPersistence(username));
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
