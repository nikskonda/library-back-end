package by.bntu.fitr.repository.user.util;

import by.bntu.fitr.model.user.util.Address;
import by.bntu.fitr.model.user.util.City;
import by.bntu.fitr.model.user.util.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAddressesByUserUsernameOrderByCreationDateTimeDesc(String username);

}
