package by.bntu.fitr.model.book;

import by.bntu.fitr.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "Book")
@Table(name = "book", schema = "public")
@SequenceGenerator(name = "id_generator", sequenceName = "book_sequence", allocationSize = 1)
@AttributeOverride(name = "id", column = @Column(name = "book_id"))
@DynamicUpdate
public class Book extends BaseEntity {

    @Column(name = "book_language", nullable = false)
    private Language language;

    @Column(name = "book_title", nullable = false)
    private String title;

    @Column(name = "book_description", nullable = false)
    private String description;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_has_author",
            joinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id", referencedColumnName = "author_id")})
    private Set<Author> author;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_has_translator",
            joinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id", referencedColumnName = "author_id")})
    private Set<Author> translator;

    @Column(name = "book_type")
    private Type type; //журнал, книга, комикс
    @Column(name = "book_age_restriction")
    private String ageRestriction;
    @Column(name = "book_rating")
    private Integer rating;
    @Column(name = "book_year")
    private Integer year;
    @Column(name = "book_status")
    private Status status; //в наличии, на руках, на складе

    @Column(name = "book_weight")
    private Integer weight;
    @Column(name = "book_size")
    private String size;
    @Column(name = "book_pages")
    private Integer pages;
    @Column(name = "book_picture_url")
    private String pictureUrl;
    @Column(name = "book_pdf_url")
    private String pdfUrl;
    @Column(name = "book_ISBN", unique = true)
    private String ISBN;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "produser_id")
    private Organization produser;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "importer_id")
    private Organization importer;

    @Column(name = "book_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal price;


    public enum Status{
        IN_STOCK, ON_HAND, UNKNOWN
    }

    public enum Type{
        BOOK, COMICS, MAGAZIN
    }
}
