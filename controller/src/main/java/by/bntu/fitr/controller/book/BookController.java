package by.bntu.fitr.controller.book;

import by.bntu.fitr.dto.PageableDto;
import by.bntu.fitr.dto.book.BookCoverDto;
import by.bntu.fitr.dto.book.BookDto;
import by.bntu.fitr.dto.book.BookSearchParameters;
import by.bntu.fitr.service.book.BookCoverService;
import by.bntu.fitr.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

@Validated
@RestController
@CrossOrigin
@RequestMapping(value = "/book")
public class BookController {

    private BookService bookService;
    private BookCoverService bookCoverService;

    @Autowired
    public BookController(BookService bookService, BookCoverService bookCoverService) {
        this.bookService = bookService;
        this.bookCoverService = bookCoverService;
    }

    @GetMapping("/{id}")
    public BookDto find(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id) {
        return bookService.find(id);
    }

    @GetMapping
    public Page<BookCoverDto> findByParameters(BookSearchParameters params,
                                               PageableDto pageableDto) {
        return bookCoverService.findByParameters(params, pageableDto);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto create(@Valid @RequestBody BookDto bookDto) {
        return bookService.save(bookDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    public BookDto update(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id,
                          @Valid @RequestBody BookDto bookDto) {
        bookDto.setId(id);
        return bookService.save(bookDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    public void remove(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id) {
        bookService.delete(id);
    }


}
