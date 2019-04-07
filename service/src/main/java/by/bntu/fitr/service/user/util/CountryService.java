package by.bntu.fitr.service.user.util;

import by.bntu.firt.NotFoundException;
import by.bntu.fitr.converter.user.util.CountryDtoConverter;
import by.bntu.fitr.dto.user.util.CountryDto;
import by.bntu.fitr.dto.user.util.StateDto;
import by.bntu.fitr.repository.user.util.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    private static final String NOT_FOUND_ERROR = "exception.not_found.author";

    private CountryRepository repository;

    private CountryDtoConverter converter;

    @Autowired
    public CountryService(CountryRepository countryRepository,
                          CountryDtoConverter countryDtoConverter) {
        this.repository = countryRepository;
        this.converter = countryDtoConverter;
    }

    public CountryDto save(CountryDto countryDto){
        return converter.convertToDto(repository.save(converter.convertFromDto(countryDto)));
//                .orElseThrow(() -> new ServiceException(String.format(SERVICE_ERROR, "creation", "user"))));
    }

    public CountryDto find(Long id){
        return converter.convertToDto(repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void delete(CountryDto countryDto){
        repository.delete(converter.convertFromDto(countryDto));
    }

}
