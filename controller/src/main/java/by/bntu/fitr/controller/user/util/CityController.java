package by.bntu.fitr.controller.user.util;

import by.bntu.fitr.dto.user.util.CityDto;
import by.bntu.fitr.dto.user.util.CountryDto;
import by.bntu.fitr.dto.user.util.StateDto;
import by.bntu.fitr.service.user.util.CityService;
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
import java.util.Set;

@Validated
@RestController
@CrossOrigin
@RequestMapping(value = "/user/city")
public class CityController {

    private CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public CityDto find(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id) {
        return cityService.find(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    @ResponseStatus(HttpStatus.CREATED)
    public CityDto create(@Valid @RequestBody CityDto cityDto) {
        return cityService.save(cityDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    public CityDto update(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id,
                          @Validated() @RequestBody CityDto cityDto) {
        cityDto.setId(id);
        return cityService.save(cityDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    public void remove(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id) {
        cityService.delete(id);
    }

    @GetMapping("/state")
    @PreAuthorize("hasAuthority('USER')")
    public Set<CityDto> findByCountry(@Valid @RequestBody StateDto stateDto) {
        return cityService.findByState(stateDto);
    }

    @GetMapping("/state/{stateId}")
    @PreAuthorize("hasAuthority('USER')")
    public Set<CityDto> findByCountryId(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long stateId) {
        return cityService.findByStateId(stateId);
    }


}
