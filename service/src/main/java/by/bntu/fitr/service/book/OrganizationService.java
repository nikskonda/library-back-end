package by.bntu.fitr.service.book;

import by.bntu.firt.NotFoundException;
import by.bntu.fitr.converter.OrganizationDtoConverter;
import by.bntu.fitr.dto.OrganizationDto;
import by.bntu.fitr.repository.book.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OrganizationService {

    private static final String NOT_FOUND_ERROR = "exception.not_found.organization";

    private OrganizationRepository repository;
    private OrganizationDtoConverter converter;

    @Autowired
    public OrganizationService(OrganizationRepository organizationRepository,
                               OrganizationDtoConverter organizationDtoConverter){
        this.repository = organizationRepository;
        this.converter = organizationDtoConverter;
    }

    public OrganizationDto save(OrganizationDto organizationDto){
        return converter.convertToDto(repository.save(converter.convertFromDto(organizationDto)));
//                .orElseThrow(() -> new ServiceException(String.format(SERVICE_ERROR, "creation", "user"))));
    }

    public OrganizationDto find(Long id){
        return converter.convertToDto(repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
    }

    public Set<OrganizationDto> findBySearchString(String searchString){
        return  converter.convertToDtoSet(repository.findBySearchString(searchString));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void delete(OrganizationDto organizationDto){
        repository.delete(converter.convertFromDto(organizationDto));
    }

}
