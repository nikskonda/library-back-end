package by.bntu.fitr.service.user.util;

import by.bntu.fitr.NotFoundException;
import by.bntu.fitr.converter.user.util.CityDtoConverter;
import by.bntu.fitr.converter.user.util.StateDtoConverter;
import by.bntu.fitr.dto.user.util.CityDto;
import by.bntu.fitr.dto.user.util.StateDto;
import by.bntu.fitr.repository.user.util.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private static final String NOT_FOUND_ERROR = "exception.notFound.city";

    private CityRepository repository;
    private CityDtoConverter converter;
    private StateDtoConverter stateDtoConverter;

    @Autowired
    public CityService(CityRepository repository, CityDtoConverter converter, StateDtoConverter stateDtoConverter) {
        this.repository = repository;
        this.converter = converter;
        this.stateDtoConverter = stateDtoConverter;
    }

    public CityDto save(CityDto cityDto){
        return converter.convertToDto(repository.save(converter.convertFromDto(cityDto)));
//                .orElseThrow(() -> new ServiceException(String.format(SERVICE_ERROR, "creation", "user"))));
    }

    public CityDto find(Long id){
        return converter.convertToDto(repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void delete(CityDto cityDto){
        repository.delete(converter.convertFromDto(cityDto));
    }

    public List<CityDto> findByState(StateDto countryDto){
        return converter.convertToDtoList(repository.findCitiesByStateOrderByNameAsc(stateDtoConverter.convertFromDto(countryDto)));
    }

    public List<CityDto> findByStateId(Long stateId){
        return converter.convertToDtoList(repository.findCitiesByStateIdOrderByNameAsc(stateId));
    }
}
