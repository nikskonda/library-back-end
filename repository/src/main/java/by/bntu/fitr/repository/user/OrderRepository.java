package by.bntu.fitr.repository.user;

import by.bntu.fitr.model.user.order.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findOrdersByAddressUserUsername(String username, Pageable pageable);

    List<Order> findOrdersByIdIn(List<Long> ids);

    @Query(value =  "select order_id " +
                    "       from ( " +
                    "select " +
                    "    order_status.order_id, " +
                    "    max(order_status.order_status) as order_status " +
                    "from " +
                    "    order_status " +
                    "group by " +
                    "    order_status.order_id) as subselect " +
                    "where " +
                    "    subselect.order_status=:status " +
                    "ORDER BY order_id ASC " +
                    "LIMIT :limit OFFSET :offset ",
            nativeQuery = true)
    List<Long> findOrdersByStatus(@Param("status") Integer status,
                                  @Param("limit") Integer limit,
                                  @Param("offset") Integer offset
//                                  @Param("order") String order
    );

    @Query(value =  "select count(*) " +
                    "   from (  " +
                    "       select  " +
                    "           order_status.order_id,  " +
                    "           max(order_status.order_status) as order_status  " +
                    "       from order_status  " +
                    "       group by  " +
                    "           order_status.order_id) as subselect  " +
                    "where subselect.order_status=:status", nativeQuery = true)
    Long countOrdersByStatus(@Param("status") Integer status);

    Page<Order> findByDetails_BookId(Long bookId, Pageable pageable);

    @Query(value =  "select subselect.order_id " +
            "       from ( " +
            "select " +
            "    order_status.order_id, " +
            "    max(order_status.order_status) as order_status " +
            "from " +
            "    order_status " +
            "group by " +
            "    order_status.order_id) as subselect " +
            "inner join order_detail od on subselect.order_id=od.order_id " +
            "WHERE " +
            "   subselect.order_status=:status AND od.book_id=:bookId " +
            "ORDER BY subselect.order_id ASC " +
            "LIMIT :limit OFFSET :offset ",
            nativeQuery = true)
    List<Long> findOrdersByStatusAndBookId(@Param("status") Integer status,
                                           @Param("bookId") Long bookId,
                                  @Param("limit") Integer limit,
                                  @Param("offset") Integer offset
//                                  @Param("order") String order
    );

    @Query(value =  "select count(*) " +
            "       from ( " +
            "select " +
            "    order_status.order_id, " +
            "    max(order_status.order_status) as order_status " +
            "from " +
            "    order_status " +
            "group by " +
            "    order_status.order_id) as subselect " +
            "inner join order_detail od on subselect.order_id=od.order_id " +
            "WHERE " +
            "   subselect.order_status=:status AND od.book_id=:bookId ", nativeQuery = true)
    Long countOrdersByStatusAndBookId(@Param("status") Integer status, @Param("bookId") Long bookId);

    Page<Order> findOrdersByAddressUserId(Long userId, Pageable pageable);

    @Query(value =  "select subselect.order_id " +
            "       from ( " +
            "select " +
            "    order_status.order_id, " +
            "    max(order_status.order_status) as order_status " +
            "from " +
            "    order_status " +
            "group by " +
            "    order_status.order_id) as subselect " +
            "inner join \"order\" o on o.order_id=subselect.order_id " +
            "inner join address \"a\" on \"a\".address_id=o.address_id " +
            "WHERE " +
            "   subselect.order_status=:status AND \"a\".user_id=:userId" +
            "ORDER BY subselect.order_id ASC " +
            "LIMIT :limit OFFSET :offset ",
            nativeQuery = true)
    List<Long> findOrdersByStatusAndUserId(@Param("status") Integer status,
                                           @Param("userId") Long userId,
                                           @Param("limit") Integer limit,
                                           @Param("offset") Integer offset
//                                  @Param("order") String order
    );

    @Query(value =  "select count(*) " +
            "       from ( " +
            "select " +
            "    order_status.order_id, " +
            "    max(order_status.order_status) as order_status " +
            "from " +
            "    order_status " +
            "group by " +
            "    order_status.order_id) as subselect " +
            "inner join \"order\" o on o.order_id=subselect.order_id " +
            "inner join address \"a\" on \"a\".address_id=o.address_id " +
            "WHERE " +
            "   subselect.order_status=:status AND \"a\".user_id=:userId" +
            "WHERE " +
            "   subselect.order_status=:status AND od.book_id=:userId ", nativeQuery = true)
    Long countOrdersByStatusAndUserId(@Param("status") Integer status, @Param("userId") Long userId);

}
