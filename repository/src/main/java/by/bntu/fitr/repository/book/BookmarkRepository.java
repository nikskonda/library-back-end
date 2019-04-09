package by.bntu.fitr.repository.book;

import by.bntu.fitr.model.book.Bookmark;
import by.bntu.fitr.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    Page<Bookmark> findByUser(User user, Pageable pageable);

}
