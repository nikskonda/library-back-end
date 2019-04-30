package by.bntu.fitr.repository.user;

import by.bntu.fitr.model.user.order.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findOrdersByAddressUserUsername(String username, Pageable pageable);


//    Page<Order> findOrdersByOrderDetailBookIdAnd(Long bookId, Pageable pageable);
//
//    Page<Order> findOrdersByUserId(Long userId, Pageable pageable);
//
//    Page<Order> findOrdersByStatus(Order.Status status, Pageable pageable);
//
//
//
//    Page<Order> findOrdersByStatusAndUserUsername(Order.Status status, String username, Pageable pageable);


}
