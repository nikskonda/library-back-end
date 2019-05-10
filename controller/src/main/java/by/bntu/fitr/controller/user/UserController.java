package by.bntu.fitr.controller.user;

import by.bntu.fitr.dto.PageableDto;
import by.bntu.fitr.dto.user.UserDataDto;
import by.bntu.fitr.dto.user.UserDto;
import by.bntu.fitr.dto.user.UserMainDataDto;
import by.bntu.fitr.service.user.UserDataService;
import by.bntu.fitr.service.user.UserMainDataService;
import by.bntu.fitr.service.user.UserService;
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
@RequestMapping(value = "/user")
public class UserController {

    private UserService userService;
    private UserDataService userDataService;
    private UserMainDataService userMainDataService;

    @Autowired
    public UserController(UserService userService, UserDataService userDataService, UserMainDataService userMainDataService) {
        this.userService = userService;
        this.userDataService = userDataService;
        this.userMainDataService = userMainDataService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserDto find(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id) {
        return userService.find(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@Valid @RequestBody UserDto userDto) {
        return userService.save(userDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public UserMainDataDto updateMainData(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id,
                                          @Valid @RequestBody UserMainDataDto user) {
        user.setId(id);
        return userMainDataService.save(user);
    }

    @GetMapping("/data/")
    @PreAuthorize("hasAuthority('USER')")
    public UserDataDto getUserData(Authentication authentication) {
        return userDataService.find(authentication.getName());
    }

    @PutMapping("/data/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public UserDataDto updateData(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id,
                                  Authentication authentication,
                                  @Validated() @RequestBody UserDataDto user) {
        user.setId(id);
        return userDataService.save(user, authentication.getName());
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<UserDataDto> findByPage(PageableDto pageableDto) {
        return userDataService.findAll(pageableDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void remove(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id) {
        userService.delete(id);
    }


}
