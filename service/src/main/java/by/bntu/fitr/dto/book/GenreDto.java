package by.bntu.fitr.dto.book;

import by.bntu.fitr.model.book.Language;
import lombok.Data;

@Data
public class GenreDto {

    private Long id;
    private Language language;
    private String name;

}
