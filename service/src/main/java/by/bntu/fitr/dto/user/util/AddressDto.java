package by.bntu.fitr.dto.user.util;

import by.bntu.fitr.dto.user.UserDto;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Data
public class AddressDto {
    @Null(message = "exception.validation.address.id.null")
    private Long id;

    private UserDto user;

    @NotNull(message = "exception.validation.address.firstName.notNull")
    @Size(min = 1, max = 30, message = "exception.validation.address.firstName.size")
    private String firstName;
    @NotNull(message = "exception.validation.address.lastName.notNull")
    @Size(min = 1, max = 30, message = "exception.validation.address.lastName.size")
    private String lastName;

    @NotNull(message = "exception.validation.address.phone.notNull")
    @Size(min = 1, max = 20, message = "exception.validation.address.phone.size")
    private String phone;

    @NotNull(message = "exception.validation.address.email.notNull")
    @Email(message = "exception.validation.address.email")
    private String email;

    @NotNull(message = "exception.validation.address.city.notNull")
    private @Valid CityDto city;

    @NotNull(message = "exception.validation.address.address.notNull")
    @Size(min = 1, max = 400, message = "exception.validation.address.address.size")
    private String address;

    @NotNull(message = "exception.validation.address.postalCode.notNull")
    @Size(min = 1, max = 20, message = "exception.validation.address.postalCode.size")
    private Integer postalCode;

    private LocalDateTime creationDateTime;
}
