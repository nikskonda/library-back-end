package by.bntu.fitr.converter.user;

import by.bntu.fitr.converter.AbstractDtoConverter;
import by.bntu.fitr.dto.user.order.OrderDetailDto;
import by.bntu.fitr.model.user.order.OrderDetail;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailDtoConverter extends AbstractDtoConverter<OrderDetail, OrderDetailDto> {

    @Autowired
    public OrderDetailDtoConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public OrderDetailDto convertToDto(OrderDetail entity) {
        return modelMapper.map(entity, OrderDetailDto.class);
    }

    @Override
    public OrderDetail convertFromDto(OrderDetailDto dto) {
        return modelMapper.map(dto, OrderDetail.class);
    }
}
