package by.bntu.fitr.dto.book;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Data
public class OrganizationDto{

    @Null(message = "exception.validation.organization.id.null")
    private Long id;

    @NotNull(message = "exception.validation.organization.title.notNull")
    @Size(min = 1, max = 255, message = "exception.validation.organization.title.size")
    private String title;

}
