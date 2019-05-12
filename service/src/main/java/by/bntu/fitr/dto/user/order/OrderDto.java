package by.bntu.fitr.dto.user.order;

import by.bntu.fitr.dto.user.util.AddressDto;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
public class OrderDto {

    @Null(message = "exception.validation.order.id.null")
    private Long id;

    @NotEmpty(message = "exception.validation.order.details.notEmpty")
    private Set<@Valid OrderDetailDto> details;

    @NotEmpty(message = "exception.validation.order.status.notEmpty")
    private List<@Valid OrderStatusDto> statusList;

    private BigDecimal totalPrice;

    @NotNull(message = "exception.validation.order.address.notNull")
    private @Valid AddressDto address;

    private LocalDateTime creationDateTime;

}
