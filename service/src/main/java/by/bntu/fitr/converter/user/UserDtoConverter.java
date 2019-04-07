package by.bntu.fitr.converter.user;

import by.bntu.fitr.converter.AbstractDtoConverter;
import by.bntu.fitr.model.user.User;
import by.bntu.fitr.dto.user.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter extends AbstractDtoConverter<User, UserDto> {

    @Autowired
    public UserDtoConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public UserDto convertToDto(User entity) {
        return modelMapper.map(entity, UserDto.class);
    }

    @Override
    public User convertFromDto(UserDto dto) {
        return modelMapper.map(dto, User.class);
    }
}
