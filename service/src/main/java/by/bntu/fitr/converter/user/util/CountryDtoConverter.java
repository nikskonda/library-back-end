package by.bntu.fitr.converter.user.util;

import by.bntu.fitr.converter.AbstractDtoConverter;
import by.bntu.fitr.dto.user.util.CountryDto;
import by.bntu.fitr.dto.user.util.StateDto;
import by.bntu.fitr.model.user.util.Country;
import by.bntu.fitr.model.user.util.State;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CountryDtoConverter extends AbstractDtoConverter<Country, CountryDto> {

    @Autowired
    public CountryDtoConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public CountryDto convertToDto(Country entity) {
        return modelMapper.map(entity, CountryDto.class);
    }

    @Override
    public Country convertFromDto(CountryDto dto) {
        return modelMapper.map(dto, Country.class);
    }
}
