package by.bntu.firt.model.book;

import by.bntu.firt.model.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class Book extends BaseEntity {

    private Language language;
    private String title;
    private String description;
    private Set<Author> author;
    private Set<Author> translator;
    private Type type; //журнал, книга, комикс
    private String ageRestriction;
    private Integer rating;
    private Integer year;
    private Status status; //в наличии, на руках, на складе

    private Integer weight;
    private String size;
    private Integer pages;
    private String pictureUrl;
    private String pdfUrl;
    private String ISBN;
    private Organization produser;
    private Organization importer;
    private BigDecimal price;


    public enum Status{
        IN_STOCK, ON_HAND, UNKNOWN
    }

    public enum Type{
        BOOK, COMICS, MAGAZIN
    }
}
