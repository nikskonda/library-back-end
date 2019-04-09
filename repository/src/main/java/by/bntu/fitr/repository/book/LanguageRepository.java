package by.bntu.fitr.repository.book;

import by.bntu.fitr.model.book.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {

    Optional<Language> findByTag(String tag);

    Boolean existsByTag(String tag);

    Language findByName(String name);

}
