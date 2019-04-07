package by.bntu.fitr.converter.book;

import by.bntu.fitr.converter.AbstractDtoConverter;
import by.bntu.fitr.model.book.PublishingHouse;
import by.bntu.fitr.dto.book.PublishingHouseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PublishingHouseDtoConverter extends AbstractDtoConverter<PublishingHouse, PublishingHouseDto> {

    @Autowired
    public PublishingHouseDtoConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public PublishingHouseDto convertToDto(PublishingHouse entity) {
        return modelMapper.map(entity, PublishingHouseDto.class);
    }
}
