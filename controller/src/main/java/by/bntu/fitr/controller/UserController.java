package by.bntu.fitr.controller;

import by.bntu.fitr.dto.UserDto;
import by.bntu.fitr.service.user.UserService;
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
    public UserDto find(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id) {
        return userService.find(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@Validated() @RequestBody UserDto userDto) {
        return userService.save(userDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public UserDto update(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id,
                          @Validated() @RequestBody UserDto user) {
        user.setId(id);
        return userService.save(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void remove(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id) {
        userService.delete(id);
    }


}
