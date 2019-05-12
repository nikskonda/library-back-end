package by.bntu.fitr.repository.user;

import by.bntu.fitr.model.user.order.Order;
import by.bntu.fitr.model.user.order.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findOrdersByAddressUserUsername(String username, Pageable pageable);

    @Query(value =
            "select qwer.order_id " +
            "       from ( " +
                "select " +
                "    order_status.order_id, " +
                "    max(order_status.order_status) as order_status " +
                "from " +
                "    order_status " +
                "group by " +
                "    order_status.order_id) as qwer " +
            "where " +
            "    qwer.order_status=:status;", nativeQuery = true)
    Page<Long> findOrdersByStatus(@Param("status") Integer status, Pageable pageable);

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
