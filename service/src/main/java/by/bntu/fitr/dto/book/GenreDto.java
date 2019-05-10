package by.bntu.fitr.dto.book;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Data
public class GenreDto {

    @Null(message = "exception.validation.genre.id.null")
    private Long id;

    @NotNull(message = "exception.validation.genre.name.notNull")
    @Size(min = 1, max = 255, message = "exception.validation.genre.name.size")
    private String name;

}
