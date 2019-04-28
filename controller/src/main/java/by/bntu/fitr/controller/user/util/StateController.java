package by.bntu.fitr.controller.user.util;

import by.bntu.fitr.dto.user.util.CountryDto;
import by.bntu.fitr.dto.user.util.StateDto;
import by.bntu.fitr.service.user.util.StateService;
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
import java.util.List;
import java.util.Set;

@Validated
@RestController
@CrossOrigin
@RequestMapping(value = "/user/state")
public class StateController {

    private StateService stateService;

    @Autowired
    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public StateDto find(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id) {
        return stateService.find(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    @ResponseStatus(HttpStatus.CREATED)
    public StateDto create(@Valid @RequestBody StateDto stateDto) {
        return stateService.save(stateDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    public StateDto update(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id,
                          @Validated() @RequestBody StateDto stateDto) {
        stateDto.setId(id);
        return stateService.save(stateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    public void remove(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id) {
        stateService.delete(id);
    }

    @GetMapping("/country")
    @PreAuthorize("hasAuthority('USER')")
    public List<StateDto> findByCountry(@Valid @RequestBody CountryDto countryDto) {
        return stateService.findByCountry(countryDto);
    }

    @GetMapping("/country/{countryId}")
    @PreAuthorize("hasAuthority('USER')")
    public List<StateDto> findByCountryId(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long countryId) {
        return stateService.findByCountryId(countryId);
    }


}
