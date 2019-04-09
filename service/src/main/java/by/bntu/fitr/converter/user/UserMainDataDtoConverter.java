package by.bntu.fitr.converter.user;

import by.bntu.fitr.converter.AbstractDtoConverter;
import by.bntu.fitr.dto.user.UserMainDataDto;
import by.bntu.fitr.model.user.User;
import by.bntu.fitr.model.user.UserMainData;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMainDataDtoConverter extends AbstractDtoConverter<UserMainData, UserMainDataDto> {

    @Autowired
    public UserMainDataDtoConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public UserMainDataDto convertToDto(UserMainData entity) {
        return modelMapper.map(entity, UserMainDataDto.class);
    }

    @Override
    public UserMainData convertFromDto(UserMainDataDto dto) {
        return modelMapper.map(dto, UserMainData.class);
    }
}
