package by.bntu.fitr.controller.user.util;

import by.bntu.fitr.dto.user.util.CountryDto;
import by.bntu.fitr.service.user.util.CountryService;
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

@Validated
@RestController
@CrossOrigin
@RequestMapping(value = "/user/country")
public class CountryController {

    private CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public CountryDto find(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id) {
        return countryService.find(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('OPERATOR')")
    @ResponseStatus(HttpStatus.CREATED)
    public CountryDto create(@Valid @RequestBody CountryDto countryDto) {
        return countryService.save(countryDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('OPERATOR')")
    public CountryDto update(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id,
                          @Validated() @RequestBody CountryDto countryDto) {
        countryDto.setId(id);
        return countryService.save(countryDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('OPERATOR')")
    public void remove(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id) {
        countryService.delete(id);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public List<CountryDto> findAll() {
        return countryService.findList();
    }

}
