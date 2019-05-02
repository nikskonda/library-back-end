package by.bntu.fitr.repository.user;

import by.bntu.fitr.model.user.order.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    Set<OrderDetail> findOrderDetailsByOrderId(Long orderId);


}
