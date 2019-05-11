package by.bntu.fitr.dto.user;

import by.bntu.fitr.dto.user.util.AddressDto;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;


@Data
public class UserDto {

    @Null(message = "exception.validation.user.id.null")
    private Long id;

    @NotNull(message = "exception.validation.user.username.notNull")
    @Pattern(regexp = "[A-Za-z]{4,30}", message = "exception.validation.user.username.pattern")
    private String username;

    @NotNull(message = "exception.validation.user.password.notNull")
    @Size(min = 5, max = 20, message = "exception.validation.user.password.size")
    private String password;

    private Set<RoleDto> authorities;

    private Boolean accountNonExpired;

    private Boolean accountNonLocked;

    private Boolean credentialsNonExpired;

    private Boolean enabled;

    @Size(min = 1, max = 255, message = "exception.validation.user.avatarUrl.size")
    private String avatarUrl;

    @Size(min = 1, max = 30, message = "exception.validation.user.firstName.size")
    private String firstName;
    @Size(min = 1, max = 30, message = "exception.validation.user.lastName.size")
    private String lastName;
    @Email(message = "exception.validation.user.email")
    private String email;

    private AddressDto address;

    public UserDto(){
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    public UserDto(String username, String password){
        this();
        this.username = username;
        this.password = password;
    }

    public UserDto(String username, String password, Set<RoleDto> authorities){
        this();
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }
}
