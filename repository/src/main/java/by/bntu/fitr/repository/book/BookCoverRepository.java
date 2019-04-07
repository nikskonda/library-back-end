package by.bntu.fitr.repository.book;

import by.bntu.fitr.model.book.BookCover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BookCoverRepository extends JpaRepository<BookCover, Long> {

    @Query("select b from Book b where b.title like %:searchString%")
    Set<BookCover> findBySearchString(@Param("searchString") String searchString);


}
