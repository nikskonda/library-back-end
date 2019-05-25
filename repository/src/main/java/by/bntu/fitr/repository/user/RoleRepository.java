package by.bntu.fitr.repository.user;

import by.bntu.fitr.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("select r from User u " +
            "INNER JOIN u.authorities r " +
            "WHERE u.username=:username ")
    Set<Role> findAllByUsername(@Param("username") String username);

    Optional<Role> findByAuthority(String authority);

//    List<Role> findOrderByPriorityAuthority();

    Boolean existsByAuthority(String authority);
}
