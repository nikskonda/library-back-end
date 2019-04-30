package by.bntu.fitr.dto.user;

import lombok.Data;

import java.util.Set;


@Data
public class UserDto {

    private Long id;
    private String username;

    private String password;

    private Set<RoleDto> authorities;

    private Boolean accountNonExpired;

    private Boolean accountNonLocked;

    private Boolean credentialsNonExpired;

    private Boolean enabled;

    private String firstName;
    private String lastName;

    private String email;

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
