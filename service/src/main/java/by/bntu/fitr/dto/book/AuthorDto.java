package by.bntu.fitr.dto.book;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Data
public class AuthorDto {

    @Null(message = "exception.validation.author.id.null")
    private Long id;

    @NotNull(message = "exception.validation.author.firstName.notNull")
    @Size(min = 1, max = 30, message = "exception.validation.author.firstName.size")
    private String firstName;
    @NotNull(message = "exception.validation.author.lastName.notNull")
    @Size(min = 1, max = 30, message = "exception.validation.author.lastName.size")
    private String lastName;

    @Size(min = 1, max = 3000, message = "exception.validation.author.description.size")
    private String description;
    @Size(min = 1, max = 255, message = "exception.validation.author.wikiLink.size")
    private String wikiLink;

}
