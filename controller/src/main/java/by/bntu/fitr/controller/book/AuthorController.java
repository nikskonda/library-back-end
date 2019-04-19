package by.bntu.fitr.controller.book;

import by.bntu.fitr.dto.book.AuthorDto;
import by.bntu.fitr.dto.book.LanguageDto;
import by.bntu.fitr.service.book.AuthorService;
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
@RequestMapping(value = "/book/author")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    public AuthorDto find(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id) {
        return authorService.find(id);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    public Set<AuthorDto> findBySearchString(String searchString, LanguageDto languageDto) {
        return authorService.findBySearchString(searchString, languageDto);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDto create(@Valid @RequestBody AuthorDto authorDto) {
        return authorService.save(authorDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    public AuthorDto update(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id,
                          @Validated() @RequestBody AuthorDto authorDto) {
        authorDto.setId(id);
        return authorService.save(authorDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    public void remove(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id) {
        authorService.delete(id);
    }


}
