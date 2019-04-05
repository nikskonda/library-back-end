package by.bntu.repository.book;

import by.bntu.firt.model.book.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {

    @Query("select a from Author a where concat(trim(firstName), ' ' ,trim(lastName)) like %:searchString%")
    Set<Author> findBySearchString(@Param("searchString") String searchString);

}
