package by.bntu.fitr.converter.user.util;

import by.bntu.fitr.converter.AbstractDtoConverter;
import by.bntu.fitr.dto.user.util.AddressDto;
import by.bntu.fitr.model.user.util.Address;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressDtoConverter extends AbstractDtoConverter<Address, AddressDto> {

    @Autowired
    public AddressDtoConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public AddressDto convertToDto(Address entity) {
        return modelMapper.map(entity, AddressDto.class);
    }

    @Override
    public Address convertFromDto(AddressDto dto) {
        return modelMapper.map(dto, Address.class);
    }
}
