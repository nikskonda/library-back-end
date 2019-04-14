package by.bntu.fitr.controller.book;

import by.bntu.fitr.dto.book.GenreDto;
import by.bntu.fitr.dto.book.LanguageDto;
import by.bntu.fitr.model.book.Language;
import by.bntu.fitr.service.book.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Set;

@Validated
@RestController
@CrossOrigin
@RequestMapping(value = "/book/genre")
public class GenreController {

    private GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    public GenreDto find(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id) {
        return genreService.find(id);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    public Set<GenreDto> findByParameters(String searchString,
                                          LanguageDto language) {
        return genreService.findByParameters(searchString, language);
    }

    @GetMapping("/popular")
    public Set<GenreDto> findPopular(LanguageDto language) {
        return genreService.getPopularGenres(language);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    @ResponseStatus(HttpStatus.CREATED)
    public GenreDto create(@Valid @RequestBody GenreDto genreDto) {
        return genreService.save(genreDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    public GenreDto update(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id,
                          @Valid @RequestBody GenreDto genreDto) {
        genreDto.setId(id);
        return genreService.save(genreDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    public void remove(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id) {
        genreService.delete(id);
    }


}
