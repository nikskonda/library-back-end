package by.bntu.repository.user;

import by.bntu.firt.model.user.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByAuthority(String authority);

}
