package by.bntu.fitr.converter;

import by.bntu.firt.model.book.Author;
import by.bntu.firt.model.book.PublishingHouse;
import by.bntu.fitr.dto.AuthorDto;
import by.bntu.fitr.dto.PublishingHouseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorDtoConverter extends AbstractDtoConverter<Author, AuthorDto> {

    @Autowired
    public AuthorDtoConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }

}
