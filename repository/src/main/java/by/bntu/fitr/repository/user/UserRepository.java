package by.bntu.fitr.repository.user;

import by.bntu.fitr.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    @Query("SELECT " +
            "CASE " +
                "WHEN (u.accountNonExpired = TRUE " +
                        "AND u.accountNonLocked = TRUE " +
                        "AND u.credentialsNonExpired = TRUE " +
                        "AND u.enabled = TRUE) " +
                    "THEN TRUE " +
            "ELSE FALSE END " +
            "FROM User u WHERE u.username = :username")
    Boolean isBaned(@Param("username") String username);
}
