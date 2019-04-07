package by.bntu.fitr.service.book;

import by.bntu.firt.NotFoundException;
import by.bntu.fitr.converter.book.LanguageDtoConverter;
import by.bntu.fitr.dto.book.LanguageDto;
import by.bntu.fitr.repository.book.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return  converter.convertToDto(repository.findByTag(tag));
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

}
