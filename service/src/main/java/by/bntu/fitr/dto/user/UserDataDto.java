package by.bntu.fitr.dto.user;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;


@Data
public class UserDataDto {

    @Null(message = "exception.validation.user.id.null")
    private Long id;
    @Null(message = "exception.validation.user.username.null")
    private String username;

    @Size(min = 1, max = 30, message = "exception.validation.user.firstName.size")
    private String firstName;
    @Size(min = 1, max = 30, message = "exception.validation.user.lastName.size")
    private String lastName;
    @Email(message = "exception.validation.user.email")
    private String email;

    public UserDataDto(){

    }

    public UserDataDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
