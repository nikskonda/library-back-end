package by.bntu.fitr.converter;

import by.bntu.fitr.dto.LanguageDto;
import by.bntu.fitr.model.book.Language;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LanguageDtoConverter extends AbstractDtoConverter<Language, LanguageDto> {

    @Autowired
    public LanguageDtoConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public LanguageDto convertToDto(Language entity) {
        return modelMapper.map(entity, LanguageDto.class);
    }

    @Override
    public Language convertFromDto(LanguageDto dto) {
        return modelMapper.map(dto, Language.class);
    }
}
