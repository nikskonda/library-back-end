package by.bntu.fitr.dto.user;

import by.bntu.fitr.dto.user.util.AddressDto;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Data
public class UserDataDto {

    @Null(message = "exception.validation.user.id.null")
    private Long id;
    @Null(message = "exception.validation.user.username.null")
    private String username;

    private List<RoleDto> authorities;

    @Size(min = 1, max = 255, message = "exception.validation.user.avatarUrl.size")
    private String avatarUrl;

    @Size(min = 1, max = 30, message = "exception.validation.user.firstName.size")
    private String firstName;
    @Size(min = 1, max = 30, message = "exception.validation.user.lastName.size")
    private String lastName;
    @Email(message = "exception.validation.user.email")
    private String email;

    private AddressDto address;

    private Boolean banned;

    public UserDataDto(){

    }

    public UserDataDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }


    public void setAuthorities(List<RoleDto> authorities) {
        this.authorities = authorities.stream()
                .sorted(Comparator.comparing(RoleDto::getPriority))
                .collect(Collectors.toList());
    }

    public void addRole(RoleDto role){
        if (this.authorities==null){
            this.authorities = new ArrayList<>();
        }
        this.authorities.add(role);
        this.authorities.stream()
                .sorted(Comparator.comparing(RoleDto::getPriority))
                .collect(Collectors.toList());
    }
}
