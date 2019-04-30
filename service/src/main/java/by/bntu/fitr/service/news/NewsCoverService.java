package by.bntu.fitr.service.news;

import by.bntu.fitr.NotFoundException;
import by.bntu.fitr.converter.news.NewsCoverDtoConverter;
import by.bntu.fitr.dto.PageableDto;
import by.bntu.fitr.dto.news.NewsCoverDto;
import by.bntu.fitr.repository.news.NewsCoverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class NewsCoverService {

    private static final String NOT_FOUND_ERROR = "exception.not_found.news";

    private NewsCoverRepository repository;
    private NewsCoverDtoConverter converter;

    @Autowired
    public NewsCoverService(NewsCoverRepository newsCoverRepository,
                            NewsCoverDtoConverter newsCoverDtoConverter) {
        this.repository = newsCoverRepository;
        this.converter = newsCoverDtoConverter;
    }

    public NewsCoverDto find(Long id) {
        return converter.convertToDto(repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
    }


    public Page<NewsCoverDto> findByParameters(String searchString, String languageTag, PageableDto pageableDto) {
        Pageable pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize(), pageableDto.getDirection(), pageableDto.getSort());
        return converter.convertToDtoPage(repository.findNewsCoversByTitleLikeAndLanguageTag(searchString, languageTag, pageable));
    }

}
