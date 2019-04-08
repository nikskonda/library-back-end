package by.bntu.fitr.controller.book;

import by.bntu.fitr.dto.book.PublishingHouseDto;
import by.bntu.fitr.service.book.PublishingHouseService;
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
@RequestMapping(value = "/book/publishingHouse")
public class PublishingHouseController {

    private PublishingHouseService service;

    @Autowired
    public PublishingHouseController(PublishingHouseService publishingHouseService) {
        this.service = publishingHouseService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    public PublishingHouseDto find(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id) {
        return service.find(id);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    public Set<PublishingHouseDto> findBySearchString(String searchString) {
        return service.findBySearchString(searchString);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    @ResponseStatus(HttpStatus.CREATED)
    public PublishingHouseDto create(@Valid @RequestBody PublishingHouseDto publishingHouseDto) {
        return service.save(publishingHouseDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    public PublishingHouseDto update(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id,
                          @Validated() @RequestBody PublishingHouseDto publishingHouseDto) {
        publishingHouseDto.setId(id);
        return service.save(publishingHouseDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    public void remove(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id) {
        service.delete(id);
    }


}
