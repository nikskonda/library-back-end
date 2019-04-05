package by.bntu.fitr.service.book;

import by.bntu.firt.NotFoundException;
import by.bntu.fitr.converter.PublishingHouseDtoConverter;
import by.bntu.fitr.dto.PublishingHouseDto;
import by.bntu.repository.book.PublishingHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PublishingHouseService {

    private static final String NOT_FOUND_ERROR = "exception.not_found.publishing_house";

    private PublishingHouseRepository repository;
    private PublishingHouseDtoConverter converter;

    @Autowired
    public PublishingHouseService(PublishingHouseRepository publishingHouseRepository,
                       PublishingHouseDtoConverter publishingHouseDtoConverter){
        this.repository = publishingHouseRepository;
        this.converter = publishingHouseDtoConverter;
    }

    public PublishingHouseDto save(PublishingHouseDto publishingHouseDto){
        return converter.convertToDto(repository.save(converter.convertFromDto(publishingHouseDto)));
//                .orElseThrow(() -> new ServiceException(String.format(SERVICE_ERROR, "creation", "user"))));
    }

    public PublishingHouseDto find(Long id){
        return converter.convertToDto(repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
    }

    public Set<PublishingHouseDto> findBySearchString(String searchString){
        return  converter.convertToDtoSet(repository.findBySearchString(searchString));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void delete(PublishingHouseDto publishingHouseDto){
        repository.delete(converter.convertFromDto(publishingHouseDto));
    }

}
