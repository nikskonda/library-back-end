package by.bntu.fitr.dto.book;

import by.bntu.fitr.model.book.Language;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
public class BookSearchParameters {

    private String searchString;
    private Set<String> genres;
    private Set<String> authors;
    private Integer minRating;
    private Integer maxRating;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Integer minYear;
    private Integer maxYear;
    private String bookLangTag;

    public BookSearchParameters() {
        this.searchString = "";
//        this.genres = new HashSet<>();
//        genres.add("");
//        this.authors = new HashSet<>();
//        authors.add("");
        this.minRating = Integer.MIN_VALUE;
        this.maxRating = Integer.MAX_VALUE;
        this.minPrice = BigDecimal.valueOf(0);
        this.maxPrice = BigDecimal.valueOf(Integer.MAX_VALUE);
        this.minYear = Integer.MIN_VALUE;
        this.maxYear = Integer.MAX_VALUE;
        this.bookLangTag = "US_en";
    }

    public void setSearchString(String searchString) {
        if (!StringUtils.isEmpty(searchString)) {
            this.searchString = searchString;
        }
    }

    public void setGenre(Set<String> genres) {
        if (genres != null && !genres.isEmpty()) {
            this.genres = genres;
        }
    }

    public void setAuthor(Set<String> authors) {
        if (authors != null && !authors.isEmpty()) {
            this.authors = authors;
        }
    }

    public void setMinRating(Integer minRating) {
        if (minRating != null && minRating <= maxRating) {
            this.minRating = minRating;
        }

    }

    public void setMaxRating(Integer maxRating) {
        if (maxRating != null && maxRating >= minRating) {
            this.maxRating = maxRating;
        }
    }

    public void setMinPrice(BigDecimal minPrice) {
        if (minPrice != null && minPrice.compareTo(maxPrice) <= 0) {
            this.minPrice = minPrice;
        }

    }

    public void setMaxPrice(BigDecimal maxPrice) {
        if (maxPrice != null && minPrice.compareTo(maxPrice) <= 0) {
            this.maxPrice = maxPrice;
        }
    }

    public void setMinYear(Integer minYear) {
        if (minYear != null && minYear <= maxYear) {
            this.minYear = minYear;
        }
    }

    public void setMaxYear(Integer maxYear) {
        if (maxYear != null && maxYear >= minYear) {
            this.maxYear = maxYear;
        }

    }

    public void setBookLangTag(String bookLangTag) {
        if (!StringUtils.isEmpty(bookLangTag)) {
            this.bookLangTag = bookLangTag;
        }
    }
}
