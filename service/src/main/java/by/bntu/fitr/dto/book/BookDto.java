package by.bntu.fitr.dto.book;

import by.bntu.fitr.model.book.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class BookDto {

    private Long id;
    private LanguageDto language;
    private String title;
    private String description;

    private Set<AuthorDto> author;

    private Set<AuthorDto> translator;

    private Set<GenreDto> genres;

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
    private PublishingHouse publishingHouse;
    private OrganizationDto producer;
    private OrganizationDto importer;
    private BigDecimal price;

}
