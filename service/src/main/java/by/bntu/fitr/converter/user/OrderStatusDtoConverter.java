package by.bntu.fitr.converter.user;

import by.bntu.fitr.converter.AbstractDtoConverter;
import by.bntu.fitr.dto.user.order.OrderStatusDto;
import by.bntu.fitr.model.user.order.OrderStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusDtoConverter extends AbstractDtoConverter<OrderStatus, OrderStatusDto> {

    @Autowired
    public OrderStatusDtoConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public OrderStatusDto convertToDto(OrderStatus entity) {
        return modelMapper.map(entity, OrderStatusDto.class);
    }

    @Override
    public OrderStatus convertFromDto(OrderStatusDto dto) {
        return modelMapper.map(dto, OrderStatus.class);
    }
}
