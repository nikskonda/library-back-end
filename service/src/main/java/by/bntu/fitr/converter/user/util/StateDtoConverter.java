package by.bntu.fitr.converter.user.util;

import by.bntu.fitr.converter.AbstractDtoConverter;
import by.bntu.fitr.dto.user.util.CityDto;
import by.bntu.fitr.dto.user.util.StateDto;
import by.bntu.fitr.model.user.util.City;
import by.bntu.fitr.model.user.util.State;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StateDtoConverter extends AbstractDtoConverter<State, StateDto> {

    @Autowired
    public StateDtoConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public StateDto convertToDto(State entity) {
        return modelMapper.map(entity, StateDto.class);
    }

    @Override
    public State convertFromDto(StateDto dto) {
        return modelMapper.map(dto, State.class);
    }
}
