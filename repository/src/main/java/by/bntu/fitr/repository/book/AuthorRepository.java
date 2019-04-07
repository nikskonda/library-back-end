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

    @Query("select a from Author a where concat(trim(firstName), ' ' ,trim(lastName)) like %:searchString%")
    Set<Author> findBySearchString(@Param("searchString") String searchString);

}
