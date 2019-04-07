package by.bntu.fitr.dto.book;

import lombok.Data;

@Data
public class AuthorDto {

    private Long id;

    private String firstName;
    private String lastName;

    private String description;
    private String wikiLink;

}
