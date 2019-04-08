package by.bntu.fitr.repository.user;

import by.bntu.fitr.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByAuthority(String authority);

    Boolean existsByAuthority(String authority);
}
