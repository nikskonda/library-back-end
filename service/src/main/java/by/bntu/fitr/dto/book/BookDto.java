package by.bntu.fitr.dto.book;

import by.bntu.fitr.model.book.Author;
import by.bntu.fitr.model.book.Book;
import by.bntu.fitr.model.book.Language;
import by.bntu.fitr.model.book.Organization;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class BookDto {

    private Long id;
    private Language language;
    private String title;
    private String description;

    private Set<Author> author;

    private Set<Author> translator;

    private Book.Type type; //журнал, книга, комикс
    private String ageRestriction;
    private Integer rating;
    private Integer year;
    private Book.Status status; //в наличии, на руках, на складе
    private Integer weight;
    private String size;
    private Integer pages;
    private String pictureUrl;
    private String thumbnailUrl;
    private String pdfUrl;
    private String ISBN;
    private Organization producer;
    private Organization importer;
    private BigDecimal price;

}
