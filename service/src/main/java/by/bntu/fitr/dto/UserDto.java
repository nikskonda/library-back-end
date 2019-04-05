package by.bntu.fitr.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserDto {

    @Null(message = "exception.validation.null.id")
    @Min(value = 0, message = "exception.validation.min.id")
    private Integer id;

    @NotNull(message = "exception.validation.pattern.username")
    @Pattern(regexp = "[A-Za-z]{4,15}", message = "exception.validation.pattern.username")
    private String username;

    @NotNull(message = "exception.validation.size.password")
    @Size(min = 6, max = 20, message = "exception.validation.size.password")
    private String password;

}
