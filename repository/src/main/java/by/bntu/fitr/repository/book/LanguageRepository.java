package by.bntu.fitr.repository.book;

import by.bntu.fitr.model.book.Language;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends PagingAndSortingRepository<Language, Long> {

    Language findByTag(String tag);

    Language findByName(String name);

}
