package by.bntu.repository.user;

import by.bntu.firt.model.book.Author;
import by.bntu.firt.model.user.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findByUsername(String username);

}
