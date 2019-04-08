package by.bntu.fitr.repository.book;

import by.bntu.fitr.model.book.Genre;
import by.bntu.fitr.model.book.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Query("select g from Genre g where g.name like %:searchString%")
    Set<Genre> findBySearchString(@Param("searchString") String searchString);

    Set<Genre> findByLanguage(Language language);

}
