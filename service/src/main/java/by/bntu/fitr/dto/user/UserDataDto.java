package by.bntu.fitr.dto.user;

import by.bntu.fitr.model.user.Role;
import by.bntu.fitr.model.user.util.City;
import lombok.Data;

import java.util.Set;


@Data
public class UserDataDto {

    private Long id;
    private String username;

    private String firstName;
    private String lastName;

    private String email;

    public UserDataDto(){

    }

    public UserDataDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
