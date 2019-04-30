package by.bntu.fitr.service.book;

import by.bntu.fitr.NotFoundException;
import by.bntu.fitr.converter.book.PublishingHouseDtoConverter;
import by.bntu.fitr.dto.book.PublishingHouseDto;
import by.bntu.fitr.model.book.PublishingHouse;
import by.bntu.fitr.repository.book.PublishingHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    }

    public PublishingHouseDto find(Long id){
        return converter.convertToDto(repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
    }

    public List<PublishingHouseDto> findBySearchString(String searchString){
        return  converter.convertToDtoList(repository.findAllByTitleLikeOrderByTitleAsc('%'+searchString+'%'));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void delete(PublishingHouseDto publishingHouseDto){
        repository.delete(converter.convertFromDto(publishingHouseDto));
    }

    public PublishingHouse getPersistences(PublishingHouse publishingHouse) {
        System.out.println("PublishingHouse Service ="+publishingHouse);
        if (publishingHouse == null) {
            return null;
        }
        if (publishingHouse.getId() != null && repository.existsById(publishingHouse.getId())) {
            return repository
                    .findById(publishingHouse.getId())
                    .orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR));
        } else {
            publishingHouse.setId(null);
            return repository.save(publishingHouse);
        }
    }

}
