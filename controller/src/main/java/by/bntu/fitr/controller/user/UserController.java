package by.bntu.fitr.controller.user;

import by.bntu.fitr.dto.PageableDto;
import by.bntu.fitr.dto.user.UserMainDataDto;
import by.bntu.fitr.service.user.UserService;
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
@RequestMapping(value = "/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserMainDataDto find(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id) {
        return userService.find(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserMainDataDto create(@Valid @RequestBody UserMainDataDto userMainDataDto) {
        return userService.save(userMainDataDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public UserMainDataDto update(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id,
                                  @Validated() @RequestBody UserMainDataDto user) {
        user.setId(id);
        return userService.save(user);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<UserMainDataDto> findByPage(PageableDto pageableDto) {
        return userService.findAll(pageableDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void remove(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id) {
        userService.delete(id);
    }


}
