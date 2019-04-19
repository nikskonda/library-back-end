package by.bntu.fitr.repository.book;

import by.bntu.fitr.model.book.Author;
import by.bntu.fitr.model.book.BookCover;
import by.bntu.fitr.model.book.Genre;
import by.bntu.fitr.model.book.Language;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Set;

@Repository
public interface BookCoverRepository extends JpaRepository<BookCover, Long> {

    @Query("select b from BookCover b where lower(b.title) like lower(:searchString)")
    Page<BookCover> findBySearchString(@Param("searchString") String searchString, Pageable pageable);

    Page<BookCover> findBookCoversByTitleLikeAndLanguageTagAndGenres(String languageTag, String searchString, Set<Genre> genres, Pageable pageable);

    Page<BookCover> findBookCoversByLanguageTagAndTitleLike(String languageTag, String searchString, Pageable pageable);


//    @Query("select b from BookCover b" +
//            "where " +
//            " b.language.tag= :languageTag AND" +
//            "(b.year between :minYear AND :maxYear) AND " +
//            "(b.price between :minPrice AND :maxPrice) AND " +
//            "(b.rating between :minRating AND :maxRating) AND " +
//            "b.genres IN (:genres)")
//    Page<BookCover> findCustom(
//            @Param("languageTag") String languageTag,
//                               @Param("minYear") Integer minYear,
//                               @Param("maxYear") Integer maxYear,
//                               @Param("minPrice") BigDecimal minPrice,
//                               @Param("maxPrice") BigDecimal maxPrice,
//                               @Param("minRating") Integer minRating,
//                               @Param("maxRating") Integer maxRating,
//                               @Param("genres") Set<Genre> genres,
//                               Pageable pageable);

}
