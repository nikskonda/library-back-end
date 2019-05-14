package by.bntu.fitr.controller.user;

import by.bntu.fitr.dto.PageableDto;
import by.bntu.fitr.dto.user.RoleDto;
import by.bntu.fitr.dto.user.UserDataDto;
import by.bntu.fitr.dto.user.UserDto;
import by.bntu.fitr.dto.user.UserMainDataDto;
import by.bntu.fitr.dto.user.UsernameAndAuthority;
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
import java.util.List;

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

    @GetMapping("/search")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<UserDataDto> findByPageAndUsername(String searchString, PageableDto pageableDto) {
        return userDataService.findAllByUsername(searchString, pageableDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void remove(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id) {
        userService.delete(id);
    }

    @PostMapping("/ban")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void ban(@RequestBody String username) {
        userMainDataService.ban(username);
    }

    @PostMapping("/clear")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void clear(@RequestBody String username) {
        userDataService.clear(username);
    }

    @PostMapping("/role")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserMainDataDto createRole(@Valid @RequestBody UsernameAndAuthority data) {
        return userMainDataService.addRoleByUsername(data.getUsername(), data.getAuthority());
    }

    @DeleteMapping("/role")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteRole(@Valid @RequestBody UsernameAndAuthority data) {
        userMainDataService.deleteRoleByUsername(data.getUsername(), data.getAuthority());
    }

    @GetMapping("/role")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<RoleDto> findAll() {
        return userMainDataService.findAllRoles();
    }

}
