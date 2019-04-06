package by.bntu.fitr.repository.book;

import by.bntu.fitr.model.book.PublishingHouse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PublishingHouseRepository extends PagingAndSortingRepository<PublishingHouse, Long> {

    @Query("Select ph from PublishingHouse ph where ph.title like:searchString")
    Set<PublishingHouse> findBySearchString(@Param("searchString") String searchString);
}
