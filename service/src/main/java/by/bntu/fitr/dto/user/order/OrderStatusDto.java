package by.bntu.fitr.dto.user.order;

import by.bntu.fitr.model.user.order.OrderStatus;
import lombok.Data;

import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@Data
public class OrderStatusDto {

    @Null(message = "exception.validation.orderStatus.id.null")
    private Long id;

    private OrderStatus.Status status;

    private LocalDateTime dateTime;

    private String comment;

    public OrderStatusDto() {
    }

    public OrderStatusDto(OrderStatus.Status status) {
        this.status = status;
    }
}
