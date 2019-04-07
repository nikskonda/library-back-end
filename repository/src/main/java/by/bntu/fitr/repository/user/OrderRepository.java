package by.bntu.fitr.repository.user;

import by.bntu.fitr.model.user.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Set<Order> findOrdersByBookId(Long bookId);

    Set<Order> findOrdersByUserId(Long userId);

    Set<Order> findOrdersByStatus(Order.Status status);

}
