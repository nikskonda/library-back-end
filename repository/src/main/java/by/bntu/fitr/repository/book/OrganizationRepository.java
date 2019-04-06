package by.bntu.fitr.repository.book;

import by.bntu.fitr.model.book.Organization;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OrganizationRepository extends PagingAndSortingRepository<Organization, Long> {

    @Query("select org from Organization org where org.title like %:searchString%")
    Set<Organization> findBySearchString(@Param("searchString") String searchString);

}
