package by.bntu.fitr.dto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class UserMainDataDto {

    @Null(message = "exception.validation.user.id.null")
    private Long id;

    @NotNull(message = "exception.validation.user.username.notNull")
    @Pattern(regexp = "[A-Za-z]{4,30}", message = "exception.validation.user.username.pattern")
    private String username;

    @NotNull(message = "exception.validation.user.password.notNull")
    @Size(min = 5, max = 20, message = "exception.validation.user.password.size")
    private String password;

    private Set<RoleDto> authorities;

}
