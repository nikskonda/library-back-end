package by.bntu.repository.book;

import by.bntu.firt.model.book.Author;
import by.bntu.firt.model.book.Organization;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface OrganizationRepository extends PagingAndSortingRepository<Organization, Long> {

    @Query("select org from Organization org where org.title like %:searchString%")
    Set<Organization> findBySearchString(@Param("searchString") String searchString);

}
