package by.bntu.fitr.repository.news;

import by.bntu.fitr.model.news.NewsCover;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface NewsCoverRepository extends JpaRepository<NewsCover, Long> {

    Page<NewsCover> findNewsCoversByTitleLikeAndLanguageTag(String searchString, String languageTag, Pageable pageable);

}
