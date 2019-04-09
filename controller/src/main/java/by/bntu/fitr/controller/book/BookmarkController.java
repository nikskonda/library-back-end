package by.bntu.fitr.controller.book;

import by.bntu.fitr.dto.PageableDto;
import by.bntu.fitr.dto.book.BookmarkDto;
import by.bntu.fitr.service.user.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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

@Validated
@RestController
@CrossOrigin
@RequestMapping(value = "/bookmark")
public class BookmarkController {

    private BookmarkService bookmarkService;

    @Autowired
    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public BookmarkDto find(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id,
                            Authentication authentication) {
        return bookmarkService.find(id, authentication.getName());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public BookmarkDto create(@Valid @RequestBody BookmarkDto bookmarkDto,
                              Authentication authentication) {
        return bookmarkService.save(bookmarkDto, authentication.getName());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public BookmarkDto update(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id,
                              @Valid @RequestBody BookmarkDto bookmarkDto,
                              Authentication authentication) {
        bookmarkDto.setId(id);
        return bookmarkService.save(bookmarkDto, authentication.getName());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('USER')")
    public void remove(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id,
                       Authentication authentication) {
        bookmarkService.delete(id, authentication.getName());
    }

    @GetMapping
    public Page<BookmarkDto> findByPage(Authentication authentication,
                                        PageableDto pageableDto) {
        return bookmarkService.findAll(authentication.getName(), pageableDto);
    }


}
