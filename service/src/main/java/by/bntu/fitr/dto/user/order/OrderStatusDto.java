package by.bntu.fitr.dto.user.order;

import by.bntu.fitr.model.user.order.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderStatusDto {

    private Long id;

    private OrderStatus.Status status;

    private LocalDateTime dateTime;

    private String comment;

}
