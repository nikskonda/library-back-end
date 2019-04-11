package by.bntu.fitr.service.user.util;

import by.bntu.fitr.NotFoundException;
import by.bntu.fitr.converter.user.util.CountryDtoConverter;
import by.bntu.fitr.converter.user.util.StateDtoConverter;
import by.bntu.fitr.dto.user.util.CountryDto;
import by.bntu.fitr.dto.user.util.StateDto;
import by.bntu.fitr.repository.user.util.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class StateService {

    private static final String NOT_FOUND_ERROR = "exception.not_found.author";

    private StateRepository repository;
    private StateDtoConverter converter;
    private CountryDtoConverter countryDtoConverter;

    @Autowired
    public StateService(StateRepository repository, StateDtoConverter converter, CountryDtoConverter countryDtoConverter) {
        this.repository = repository;
        this.converter = converter;
        this.countryDtoConverter = countryDtoConverter;
    }

    public StateDto save(StateDto stateDto){
        return converter.convertToDto(repository.save(converter.convertFromDto(stateDto)));
//                .orElseThrow(() -> new ServiceException(String.format(SERVICE_ERROR, "creation", "user"))));
    }

    public StateDto find(Long id){
        return converter.convertToDto(repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void delete(StateDto stateDto){
        repository.delete(converter.convertFromDto(stateDto));
    }

    public Set<StateDto> findByCountry(CountryDto countryDto){
        return converter.convertToDtoSet(repository.findStatesByCountry(countryDtoConverter.convertFromDto(countryDto)));
    }

    public Set<StateDto> findByCountryId(Long countryId){
        return converter.convertToDtoSet(repository.findStatesByCountryId(countryId));
    }
}
