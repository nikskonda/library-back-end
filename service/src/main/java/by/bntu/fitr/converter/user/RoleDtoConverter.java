package by.bntu.fitr.converter.user;

import by.bntu.fitr.converter.AbstractDtoConverter;
import by.bntu.fitr.dto.user.RoleDto;
import by.bntu.fitr.model.user.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleDtoConverter extends AbstractDtoConverter<Role, RoleDto> {

    @Autowired
    public RoleDtoConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public RoleDto convertToDto(Role entity) {
        return modelMapper.map(entity, RoleDto.class);
    }

    @Override
    public Role convertFromDto(RoleDto dto) {
        return modelMapper.map(dto, Role.class);
    }
}
