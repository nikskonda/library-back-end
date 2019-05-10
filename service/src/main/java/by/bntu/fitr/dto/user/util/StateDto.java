package by.bntu.fitr.dto.user.util;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class StateDto {

//    @Null(message = "exception.validation.state.id.null")
    private Long id;

    @NotNull(message = "exception.validation.state.name.notNull")
    @Size(min = 1, max = 255, message = "exception.validation.state.name.size")
    private String name;
    @NotNull(message = "exception.validation.state.state.notNull")
    private @Valid CountryDto country;

}
