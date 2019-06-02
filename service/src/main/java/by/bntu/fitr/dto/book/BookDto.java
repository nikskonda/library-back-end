package by.bntu.fitr.dto.book;

import by.bntu.fitr.model.book.Book;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class BookDto {

    @Null(message = "exception.validation.book.id.null")
    private Long id;
    @NotNull(message = "exception.validation.book.language.notNull")
    private LanguageDto language;

    @NotNull(message = "exception.validation.book.title.notNull")
    @Size(min = 1, max = 255, message = "exception.validation.book.title.size")
    private String title;

    @Size(min = 1, max = 3000, message = "exception.validation.book.description.size")
    private String description;

    private Set<AuthorDto> authors;

    private Set<AuthorDto> translators;

    @NotEmpty(message = "exception.validation.book.genres.notEmpty")
    private Set<GenreDto> genres;

    @NotNull(message = "exception.validation.book.type.notNull")
    private Book.Type type; //журнал, книга, комикс

    @Size(min = 1, max = 255, message = "exception.validation.book.ageRestriction.size")
    private String ageRestriction;

    @Min(value = 0, message = "exception.validation.book.rating.min")
    @Max(value = 100, message = "exception.validation.book.rating.max")
    private Integer rating;

    @Min(value = -1, message = "exception.validation.book.year.min")
    @Max(value = 2020, message = "exception.validation.book.year.max")
    private Integer year;

//    private Book.Status status; //в наличии, на руках, на складе

    @Min(value = 1, message = "exception.validation.book.weight.min")
    private Integer weight;

    @Size(min = 1, max = 255, message = "exception.validation.book.size.size")
    private String size;

    @Min(value = 1, message = "exception.validation.book.pages.min")
    private Integer pages;
    @NotNull(message = "exception.validation.book.pictureUrl.notNull")
    @Size(min = 1, max = 255, message = "exception.validation.book.pictureUrl.size")
    private String pictureUrl;
    @NotNull(message = "exception.validation.book.thumbnailUrl.notNull")
    @Size(min = 1, max = 255, message = "exception.validation.book.thumbnailUrl.size")
    private String thumbnailUrl;
    @Size(min = 1, max = 255, message = "exception.validation.book.pdfUrl.size")
    private String pdfUrl;
    @Size(min = 1, max = 255, message = "exception.validation.book.ePubUrl.size")
    private String ePubUrl;

    @Size(min = 1, max = 255, message = "exception.validation.book.isbn.size")
    private String isbn;
    private PublishingHouseDto publishingHouse;
    private OrganizationDto producer;
    private OrganizationDto importer;

//    @Null(message = "exception.validation.book.price.null")
//    private BigDecimal price;

    @NotNull(message = "exception.validation.book.inLibraryUseOnly.notNull")
    private Boolean inLibraryUseOnly;

    @NotNull(message = "exception.validation.book.count.notNull")
    @Min(value = 1, message = "exception.validation.book.count.min")
    private Integer count;
    public BookDto() {
        this.rating = 1;
        this.count = 1;
        this.year = -1;
    }

    public Boolean isInLibraryUseOnly() {
        return inLibraryUseOnly;
    }
}
