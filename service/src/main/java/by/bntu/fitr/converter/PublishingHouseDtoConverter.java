package by.bntu.fitr.converter;

import by.bntu.firt.model.book.PublishingHouse;
import by.bntu.firt.model.user.User;
import by.bntu.fitr.dto.PublishingHouseDto;
import by.bntu.fitr.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PublishingHouseDtoConverter extends AbstractDtoConverter<PublishingHouse, PublishingHouseDto> {

    @Autowired
    public PublishingHouseDtoConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }

}
