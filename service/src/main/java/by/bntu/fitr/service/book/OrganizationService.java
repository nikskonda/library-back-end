package by.bntu.fitr.service.book;

import by.bntu.fitr.NotFoundException;
import by.bntu.fitr.converter.book.OrganizationDtoConverter;
import by.bntu.fitr.dto.book.OrganizationDto;
import by.bntu.fitr.model.book.Organization;
import by.bntu.fitr.repository.book.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class OrganizationService {

    private static final String NOT_FOUND_ERROR = "exception.notFound.organization";

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

    public List<OrganizationDto> findBySearchString(String searchString){
        return  converter.convertToDtoList(repository.findAllByTitleLikeOrderByTitleAsc("%"+searchString+"%"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void delete(OrganizationDto organizationDto){
        repository.delete(converter.convertFromDto(organizationDto));
    }

    public Organization getPersistence(Organization organization) {
        if (organization == null) {
            return null;
        }
        if (organization.getId() != null && repository.existsById(organization.getId())) {
            return repository
                    .findById(organization.getId())
                    .orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR));
        }
        if (!StringUtils.isEmpty(organization.getTitle())){
            return repository.findByTitle(organization.getTitle()).orElse(repository.save(organization));
        } else {
            return null;
        }
    }
}
