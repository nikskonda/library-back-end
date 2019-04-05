package by.bntu.firt.model.book;

import by.bntu.firt.model.BaseEntity;
import lombok.Data;


@Data
public class Author extends BaseEntity {


    private String firstName;
    private String lastName;

    private String description;
    private String wikiLink;

}
