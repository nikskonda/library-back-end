package by.bntu.fitr.repository.user;

import by.bntu.fitr.model.user.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findOrdersByBookId(Long bookId, Pageable pageable);

    Page<Order> findOrdersByUserId(Long userId, Pageable pageable);

    Page<Order> findOrdersByStatus(Order.Status status, Pageable pageable);

    Page<Order> findOrdersByUserUsername(String username, Pageable pageable);

    Page<Order> findOrdersByStatusAndUserUsername(Order.Status status, String username, Pageable pageable);


}
