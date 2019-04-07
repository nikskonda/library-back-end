package by.bntu.fitr.converter.book;

import by.bntu.fitr.converter.AbstractDtoConverter;
import by.bntu.fitr.model.book.Organization;
import by.bntu.fitr.dto.book.OrganizationDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrganizationDtoConverter extends AbstractDtoConverter<Organization, OrganizationDto> {

    @Autowired
    public OrganizationDtoConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public OrganizationDto convertToDto(Organization entity) {
        return modelMapper.map(entity, OrganizationDto.class);
    }
}
