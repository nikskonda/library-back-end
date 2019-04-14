package by.bntu.fitr.repository.book;

import by.bntu.fitr.model.book.BookCover;
import by.bntu.fitr.model.book.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BookCoverRepository extends JpaRepository<BookCover, Long> {

    @Query("select b from BookCover b where b.title like %:searchString%")
    Page<BookCover> findBySearchString(@Param("searchString") String searchString, Pageable pageable);

    Page<BookCover> findBookCoversByGenres(Set<Genre> genres, Pageable pageable);


}
