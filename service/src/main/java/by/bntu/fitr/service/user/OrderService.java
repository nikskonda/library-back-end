package by.bntu.fitr.service.user;

import by.bntu.firt.NotFoundException;
import by.bntu.fitr.converter.user.OrderDtoConverter;
import by.bntu.fitr.converter.user.util.CountryDtoConverter;
import by.bntu.fitr.dto.PageableDto;
import by.bntu.fitr.dto.user.OrderDto;
import by.bntu.fitr.dto.user.UserDto;
import by.bntu.fitr.dto.user.util.CountryDto;
import by.bntu.fitr.model.user.Order;
import by.bntu.fitr.model.user.User;
import by.bntu.fitr.repository.user.OrderRepository;
import by.bntu.fitr.repository.user.util.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OrderService {

    private static final String NOT_FOUND_ERROR = "exception.not_found.author";

    private OrderRepository repository;
    private OrderDtoConverter converter;
    private UserService userService;

    @Autowired
    public OrderService(OrderRepository repository, OrderDtoConverter converter, UserService userService) {
        this.repository = repository;
        this.converter = converter;
        this.userService = userService;
    }

    public OrderDto save(OrderDto orderDto){
//    public OrderDto save(OrderDto orderDto, String username){
        Order order = converter.convertFromDto(orderDto);
//        order.setUser((User)userService.loadUserByUsername(username));
        return converter.convertToDto(repository.save(order));
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

    public Page<OrderDto> findOrdersByBookId(Long bookId, PageableDto pageableDto){
        Pageable pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize(), pageableDto.getDirection(), pageableDto.getSort());
        return converter.convertToDtoPage(repository.findOrdersByBookId(bookId, pageable));
    }

    public Page<OrderDto> findOrdersByStatus(Order.Status status, PageableDto pageableDto){
        Pageable pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize(), pageableDto.getDirection(), pageableDto.getSort());
        return converter.convertToDtoPage(repository.findOrdersByStatus(status, pageable));
    }

    public Page<OrderDto> findOrdersByUserId(Long userId, PageableDto pageableDto){
        Pageable pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize(), pageableDto.getDirection(), pageableDto.getSort());
        return converter.convertToDtoPage(repository.findOrdersByUserId(userId, pageable));
    }

    public Page<OrderDto> findAll(PageableDto pageableDto){
        Pageable pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize(), pageableDto.getDirection(), pageableDto.getSort());
        return converter.convertToDtoPage(repository.findAll(pageable));
    }

}
