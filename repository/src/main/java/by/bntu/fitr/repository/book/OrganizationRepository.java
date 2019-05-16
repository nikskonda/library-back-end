package by.bntu.fitr.repository.book;

import by.bntu.fitr.model.book.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    List<Organization> findAllByTitleLikeOrderByTitleAsc(String searchString);

    Optional<Organization> findByTitle(String title);
}
