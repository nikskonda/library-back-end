package by.bntu.fitr.repository.user;

import by.bntu.fitr.model.user.order.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {

    List<OrderStatus> findOrderStatusesByOrderIdOrderByDateTimeAsc(Long orderId);

//    @Query( "SELECT s.order, MAX(s.dateTime) " +
//                "FROM OrderStatus s " +
//            "GROUP BY s.order " +
//                "HEAVING s.status=:status")
//    Page<OrderStatus> findOrderStatusesByStatus(OrderStatus.Status status, Pageable pageable);

//    @Query("SELECT s.order " +
//            "FROM OrderStatus s " +
//            "where s.status=:status " +
//            "group by s.order " +
//            "having MAX(s.dateTime)=s.dateTime")
    Page<Long> findAllByStatus(@Param("status") OrderStatus.Status status, Pageable pageable);
}
