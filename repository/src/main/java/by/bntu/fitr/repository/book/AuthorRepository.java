package by.bntu.fitr.repository.book;

import by.bntu.fitr.model.book.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(value = "SELECT a.author_id, a.author_first_name, a.author_last_name, a.author_description, a.author_wiki_link " +
                        "FROM public.author a " +
                    "INNER JOIN book_has_authors ba ON ba.author_id = a.author_id " +
                    "INNER JOIN book b ON b.book_id = ba.book_id " +
                    "INNER JOIN \"language\" l ON b.language_id = l.language_id " +
                        "WHERE (b.language_id = :langId OR l.language_tag = :langTag) " +
                            "AND (lower(concat(trim(a.author_first_name), ' ' ,trim(a.author_last_name))) like lower(:searchString) OR " +
                    "lower(concat(trim(a.author_last_name), ' ' ,trim(a.author_first_name))) like lower(:searchString))" +
                        "GROUP BY a.author_id",
            nativeQuery=true)
    Set<Author> findBySearchString(@Param("searchString") String searchString,
                                   @Param("langId") Long langId,
                                   @Param("langTag") String langTag);

}
