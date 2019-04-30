package by.bntu.fitr.converter.user;

import by.bntu.fitr.converter.AbstractDtoConverter;
import by.bntu.fitr.dto.user.order.OrderDto;
import by.bntu.fitr.model.user.order.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderDtoConverter extends AbstractDtoConverter<Order, OrderDto> {

    @Autowired
    public OrderDtoConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public OrderDto convertToDto(Order entity) {
        return modelMapper.map(entity, OrderDto.class);
    }

    @Override
    public Order convertFromDto(OrderDto dto) {
        return modelMapper.map(dto, Order.class);
    }
}
