package by.bntu.fitr.repository.user;

import by.bntu.fitr.model.user.UserData;
import by.bntu.fitr.model.user.UserMainData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserMainDataRepository extends JpaRepository<UserMainData, Long> {

    Optional<UserMainData> findByUsername(String username);

    Boolean existsByUsername(String username);

}
