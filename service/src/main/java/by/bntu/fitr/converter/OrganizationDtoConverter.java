package by.bntu.fitr.converter;

import by.bntu.firt.model.book.Organization;
import by.bntu.fitr.dto.OrganizationDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrganizationDtoConverter extends AbstractDtoConverter<Organization, OrganizationDto> {

    @Autowired
    public OrganizationDtoConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }

}
