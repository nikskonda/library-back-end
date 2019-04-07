package by.bntu.fitr.dto.book;

import by.bntu.fitr.model.book.Author;
import by.bntu.fitr.model.book.Book;
import by.bntu.fitr.model.book.Language;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class BookCoverDto {

    private Long id;
    private Language language;

    private String title;
    private Set<Author> author;
    private Set<Author> translator;

    private Book.Type type; //журнал, книга, комикс
    private String ageRestriction;
    private Integer rating;
    private Integer year;
    private Book.Status status; //в наличии, на руках, на складе

    private String thumbnailUrl;

    private BigDecimal price;
}
