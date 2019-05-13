package by.bntu.fitr.dto.book;

import by.bntu.fitr.model.book.Author;
import by.bntu.fitr.model.book.Book;
import by.bntu.fitr.model.book.Language;
import lombok.Data;

import java.util.Set;

@Data
public class BookCoverDto {

    private Long id;
    private Language language;

    private String title;
    private Set<Author> authors;
    private Set<Author> translators;

    private Set<GenreDto> genres;

    private Book.Type type; //журнал, книга, комикс
    private String ageRestriction;
    private Integer rating;
    private Integer year;

    private String thumbnailUrl;

    private Boolean inLibraryUseOnly;

    private Integer count;

    public BookCoverDto() {
        this.rating = 0;
        this.count = 1;
        this.year = -1;
    }

    public Boolean isInLibraryUseOnly() {
        return inLibraryUseOnly;
    }
}
