package by.bntu.fitr.repository.book;

import by.bntu.fitr.model.book.Genre;
import by.bntu.fitr.model.book.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {


    List<Genre> findAllByNameLikeOrderByNameAsc(String searchString);

    Optional<Genre> findByName(String name);

    Boolean existsByName(String name);

    @Query(value = "SELECT g.genre_id, g.genre_name, count(g.genre_id) as genre_count " +
                        "FROM public.genre g " +
                    "INNER JOIN book_has_genres bg ON bg.genre_id = g.genre_id " +
                    "INNER JOIN book b ON b.book_id = bg.book_id " +
                    "INNER JOIN \"language\" l ON b.language_id = l.language_id " +
                        "WHERE l.language_tag=:langTag " +
                    "GROUP BY g.genre_id " +
                        "ORDER BY genre_count DESC " +
                            "LIMIT :count",
            nativeQuery=true)
    Set<Genre> findByPopularGenresByLang(@Param("count") Integer count,
                                         @Param("langTag") String langTag);

}
