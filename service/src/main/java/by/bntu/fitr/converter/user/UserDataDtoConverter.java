package by.bntu.fitr.converter.user;

import by.bntu.fitr.converter.AbstractDtoConverter;
import by.bntu.fitr.dto.user.UserDataDto;
import by.bntu.fitr.dto.user.UserMainDataDto;
import by.bntu.fitr.model.user.User;
import by.bntu.fitr.model.user.UserData;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDataDtoConverter extends AbstractDtoConverter<UserData, UserDataDto> {

    @Autowired
    public UserDataDtoConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public UserDataDto convertToDto(UserData entity) {
        return modelMapper.map(entity, UserDataDto.class);
    }

    @Override
    public UserData convertFromDto(UserDataDto dto) {
        return modelMapper.map(dto, UserData.class);
    }
}
