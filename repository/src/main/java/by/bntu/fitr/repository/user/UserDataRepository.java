package by.bntu.fitr.repository.user;

import by.bntu.fitr.model.user.UserData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {

    Optional<UserData> findByUsername(String username);

    Boolean existsByUsername(String username);

    Page<UserData> findUserDataByUsernameLike(String searchString, Pageable pageable);

}
