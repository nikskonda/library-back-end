package by.bntu.fitr.dto.user;

import by.bntu.fitr.model.user.Role;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.Set;

@Data
public class UserDto {

//    @Null(message = "exception.validation.null.id")
//    @Min(value = 0, message = "exception.validation.min.id")
    private Long id;

//    @NotNull(message = "exception.validation.pattern.username")
//    @Pattern(regexp = "[A-Za-z]{4,15}", message = "exception.validation.pattern.username")
    private String username;

//    @NotNull(message = "exception.validation.size.password")
//    @Size(min = 5, max = 20, message = "exception.validation.size.password")
    private String password;

    private Set<RoleDto> authorities;

}
