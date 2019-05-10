package by.bntu.fitr.dto.user.util;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class CityDto{

    //@Null(message = "exception.validation.city.id.null")
    private Long id;

    @NotNull(message = "exception.validation.city.name.notNull")
    @Size(min = 1, max = 255, message = "exception.validation.city.name.size")
    private String name;

    @NotNull(message = "exception.validation.city.state.notNull")
    private @Valid StateDto state;

}
