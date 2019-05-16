package by.bntu.fitr.repository.user.util;

import by.bntu.fitr.model.user.util.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
