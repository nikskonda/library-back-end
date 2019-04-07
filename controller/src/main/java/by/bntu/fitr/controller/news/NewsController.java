package by.bntu.fitr.controller.news;

import by.bntu.fitr.dto.news.NewsCoverDto;
import by.bntu.fitr.dto.news.NewsDto;
import by.bntu.fitr.service.news.NewsCoverService;
import by.bntu.fitr.service.news.NewsService;
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
@RequestMapping(value = "/news")
public class NewsController {

    private NewsService newsService;
    private NewsCoverService newsCoverService;

    @Autowired
    public NewsController(NewsService newsService, NewsCoverService newsCoverService) {
        this.newsService = newsService;
        this.newsCoverService = newsCoverService;
    }

    @GetMapping("/{id}")
    public NewsDto find(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id) {
        return newsService.find(id);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    public Set<NewsCoverDto> findBySearchString(@PathVariable String searchString) {
        return newsCoverService.findBySearchString(searchString);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    @ResponseStatus(HttpStatus.CREATED)
    public NewsDto create(@Valid @RequestBody NewsDto newsDto) {
        return newsService.save(newsDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    public NewsDto update(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id,
                          @Validated() @RequestBody NewsDto newsDto) {
        newsDto.setId(id);
        return newsService.save(newsDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    public void remove(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id) {
        newsService.delete(id);
    }


    @GetMapping("/list")
    public Set<NewsCoverDto> findByPage() {
        return newsCoverService.findAll();
    }

}
