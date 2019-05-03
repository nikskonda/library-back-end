package by.bntu.fitr.repository.user;

import by.bntu.fitr.model.user.order.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {

    List<OrderStatus> findOrderStatusesByOrderIdOrderByDateTimeAsc(Long orderId);

}
