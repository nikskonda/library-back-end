package by.bntu.fitr.repository.user.order;

import by.bntu.fitr.model.user.order.UserOrder;
import by.bntu.fitr.model.user.util.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {

   @Query("select a from UserOrder uo " +
           "INNER JOIN uo.address a " +
           "INNER JOIN uo.user u " +
           "WHERE u.username=:username " +
           "ORDER BY uo.lastModification DESC ")
   List<Address> findAddressesByUserUsername(@Param("username") String username);

}
