package by.bntu.fitr.service.user;

import by.bntu.firt.NotFoundException;
import by.bntu.fitr.converter.user.OrderDtoConverter;
import by.bntu.fitr.converter.user.util.CountryDtoConverter;
import by.bntu.fitr.dto.user.OrderDto;
import by.bntu.fitr.dto.user.util.CountryDto;
import by.bntu.fitr.model.user.Order;
import by.bntu.fitr.repository.user.OrderRepository;
import by.bntu.fitr.repository.user.util.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OrderService {

    private static final String NOT_FOUND_ERROR = "exception.not_found.author";

    private OrderRepository repository;

    private OrderDtoConverter converter;

    @Autowired
    public OrderService(OrderRepository repository, OrderDtoConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public OrderDto save(OrderDto orderDto){
        return converter.convertToDto(repository.save(converter.convertFromDto(orderDto)));
//                .orElseThrow(() -> new ServiceException(String.format(SERVICE_ERROR, "creation", "user"))));
    }

    public OrderDto find(Long id){
        return converter.convertToDto(repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void delete(OrderDto orderDto){
        repository.delete(converter.convertFromDto(orderDto));
    }

    public Set<OrderDto> findOrdersByBookId(Long bookId){
        return converter.convertToDtoSet(repository.findOrdersByBookId(bookId));
    }

    public Set<OrderDto> findOrdersByStatus(Order.Status status){
        return converter.convertToDtoSet(repository.findOrdersByStatus(status));
    }

    public Set<OrderDto> findOrdersByUserId(Long userId){
        return converter.convertToDtoSet(repository.findOrdersByUserId(userId));
    }

}
