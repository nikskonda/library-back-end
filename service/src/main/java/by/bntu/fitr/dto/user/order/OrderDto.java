package by.bntu.fitr.dto.user.order;

import by.bntu.fitr.dto.user.util.AddressDto;
import by.bntu.fitr.model.user.order.OrderDetail;
import by.bntu.fitr.model.user.order.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
public class OrderDto {

    private Long id;

    private Set<OrderDetailDto> details;

    private List<OrderStatusDto> statusList;

    private BigDecimal totalPrice;

    private AddressDto address;

    private LocalDateTime creationDateTime;

}
