package by.bntu.fitr.converter.user.util;

import by.bntu.fitr.converter.AbstractDtoConverter;
import by.bntu.fitr.dto.book.AuthorDto;
import by.bntu.fitr.dto.user.util.CityDto;
import by.bntu.fitr.model.book.Author;
import by.bntu.fitr.model.user.util.City;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityDtoConverter extends AbstractDtoConverter<City, CityDto> {

    @Autowired
    public CityDtoConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public CityDto convertToDto(City entity) {
        return modelMapper.map(entity, CityDto.class);
    }

    @Override
    public City convertFromDto(CityDto cityDto) {
        return modelMapper.map(cityDto, City.class);
    }
}
