package by.bntu.fitr.repository.user;

import by.bntu.fitr.model.user.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByAuthority(String authority);

}
