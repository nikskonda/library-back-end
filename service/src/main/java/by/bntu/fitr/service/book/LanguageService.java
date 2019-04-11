package by.bntu.fitr.service.book;

import by.bntu.fitr.NotFoundException;
import by.bntu.fitr.converter.book.LanguageDtoConverter;
import by.bntu.fitr.dto.book.LanguageDto;
import by.bntu.fitr.model.book.Language;
import by.bntu.fitr.repository.book.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class LanguageService {

    private static final String NOT_FOUND_ERROR = "exception.not_found.organization";

    private LanguageRepository repository;
    private LanguageDtoConverter converter;

    @Autowired
    public LanguageService(LanguageRepository languageRepository,
                           LanguageDtoConverter languageDtoConverter){
        this.repository = languageRepository;
        this.converter = languageDtoConverter;
    }

    public LanguageDto save(LanguageDto languageDto){
        return converter.convertToDto(repository.save(converter.convertFromDto(languageDto)));
//                .orElseThrow(() -> new ServiceException(String.format(SERVICE_ERROR, "creation", "user"))));
    }

    public LanguageDto find(Long id){
        return converter.convertToDto(repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
    }

    public LanguageDto findByTag(String tag){
        return  converter.convertToDto(repository.findByTag(tag).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
    }

    public LanguageDto findByName(String name){
        return  converter.convertToDto(repository.findByName(name));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void delete(LanguageDto languageDto){
        repository.delete(converter.convertFromDto(languageDto));
    }

    public Language getPersistents(Language language) {
        if (language == null) {
            return null;
        }
        if (language.getId() != null && repository.existsById(language.getId())) {
            return repository
                    .findById(language.getId())
                    .orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR));
        } else if (!StringUtils.isEmpty(language.getTag()) && repository.existsByTag(language.getTag())) {
            return repository
                    .findByTag(language.getTag())
                    .orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR));
        } else {
            language.setId(null);
            return repository.save(language);
        }

    }
}
