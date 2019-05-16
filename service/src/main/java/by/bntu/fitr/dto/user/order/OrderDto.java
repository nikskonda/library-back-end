package by.bntu.fitr.dto.user.order;

import by.bntu.fitr.dto.user.UserDataDto;
import by.bntu.fitr.dto.user.util.AddressDto;
import by.bntu.fitr.model.user.order.UserOrder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class OrderDto {

    @Null(message = "exception.validation.order.id.null")
    private Long id;

    @NotEmpty(message = "exception.validation.order.details.notEmpty")
    private Set<@Valid OrderDetailDto> details;

    //@NotEmpty(message = "exception.validation.order.status.notEmpty")
    private List<@Valid OrderStatusDto> statusList;

    private BigDecimal totalPrice;

    private UserOrderDto userOrder;

    private AddressDto address;

    private UserDataDto user;

    private LocalDateTime creationDateTime;

    public void setStatusList(List<OrderStatusDto> statusList) {
        statusList.stream()
                .sorted(Comparator.comparing(OrderStatusDto::getDateTime).reversed())
                .collect(Collectors.toList());
        this.statusList = statusList;
    }

    public UserOrder getUserOrder() {
        return null;
    }

    public void setUserOrder(UserOrderDto userOrder) {
        this.userOrder = userOrder;
        this.address = this.userOrder.getAddress();
        this.user = this.userOrder.getUser();
    }

}
