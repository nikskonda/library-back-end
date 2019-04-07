package by.bntu.fitr.controller.book;

import by.bntu.fitr.dto.book.OrganizationDto;
import by.bntu.fitr.service.book.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Set;

@Validated
@RestController
@CrossOrigin
@RequestMapping(value = "/book/organization")
public class OrganizationController {

    private OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    public OrganizationDto find(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id) {
        return organizationService.find(id);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    public Set<OrganizationDto> findBySearchString(@PathVariable String searchString) {
        return organizationService.findBySearchString(searchString);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    @ResponseStatus(HttpStatus.CREATED)
    public OrganizationDto create(@Valid @RequestBody OrganizationDto organizationDto) {
        return organizationService.save(organizationDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    public OrganizationDto update(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id,
                          @Validated() @RequestBody OrganizationDto organizationDto) {
        organizationDto.setId(id);
        return organizationService.save(organizationDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    public void remove(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id) {
        organizationService.delete(id);
    }


}
