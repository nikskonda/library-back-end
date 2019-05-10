package by.bntu.fitr.dto.user.util;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class CountryDto{

//    @Null(message = "exception.validation.country.id.null")
    private Long id;

    @NotNull(message = "exception.validation.country.name.notNull")
    @Size(min = 1, max = 255, message = "exception.validation.country.name.size")
    private String name;

}
