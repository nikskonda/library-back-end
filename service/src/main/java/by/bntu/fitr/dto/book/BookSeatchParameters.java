package by.bntu.fitr.dto.book;

import lombok.Data;

import java.util.Set;

@Data
public class BookSeatchParameters {

    private String searchString;
    private Set<String> genres;
    private Set<String> author;

}
