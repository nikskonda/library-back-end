package by.bntu.fitr.repository.user;

import by.bntu.fitr.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepository extends JpaRepository<User, Long> {

}
