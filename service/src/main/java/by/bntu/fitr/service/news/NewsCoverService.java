package by.bntu.fitr.service.news;

import by.bntu.firt.NotFoundException;
import by.bntu.fitr.converter.news.NewsCoverDtoConverter;
import by.bntu.fitr.converter.news.NewsDtoConverter;
import by.bntu.fitr.dto.news.NewsCoverDto;
import by.bntu.fitr.dto.news.NewsDto;
import by.bntu.fitr.model.news.NewsCover;
import by.bntu.fitr.repository.news.NewsCoverRepository;
import by.bntu.fitr.repository.news.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class NewsCoverService {

    private static final String NOT_FOUND_ERROR = "exception.not_found.news";

    private NewsCoverRepository repository;
    private NewsCoverDtoConverter converter;

    @Autowired
    public NewsCoverService(NewsCoverRepository newsCoverRepository,
                            NewsCoverDtoConverter newsCoverDtoConverter){
        this.repository = newsCoverRepository;
        this.converter = newsCoverDtoConverter;
    }

    public NewsCoverDto find(Long id){
        return converter.convertToDto(repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
    }

    public Set<NewsCoverDto> findBySearchString(String searchString){
        return  converter.convertToDtoSet(repository.findBySearchString(searchString));
    }

    public Set<NewsCoverDto> findAll(){
        return  converter.convertToDtoSet(repository.findAll());
    }


}
