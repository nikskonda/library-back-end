package by.bntu.fitr.service.user.order;

import by.bntu.fitr.AccessDeniedException;
import by.bntu.fitr.NotFoundException;
import by.bntu.fitr.UnsupportedOperationException;
import by.bntu.fitr.converter.AbstractDtoConverter;
import by.bntu.fitr.converter.user.OrderDtoConverter;
import by.bntu.fitr.converter.user.OrderStatusDtoConverter;
import by.bntu.fitr.dto.PageableDto;
import by.bntu.fitr.dto.user.order.OrderDetailDto;
import by.bntu.fitr.dto.user.order.OrderDto;
import by.bntu.fitr.dto.user.order.OrderStatusDto;
import by.bntu.fitr.model.user.User;
import by.bntu.fitr.model.user.order.Order;
import by.bntu.fitr.model.user.order.OrderDetail;
import by.bntu.fitr.model.user.order.OrderStatus;
import by.bntu.fitr.model.user.util.Address;
import by.bntu.fitr.repository.user.OrderDetailRepository;
import by.bntu.fitr.repository.user.OrderRepository;
import by.bntu.fitr.repository.user.OrderStatusRepository;
import by.bntu.fitr.service.book.BookService;
import by.bntu.fitr.service.user.UserService;
import by.bntu.fitr.service.user.util.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private static final String NOT_FOUND_ERROR = "exception.not_found.order";
    private static final String ROLE_FOR_ORDER_EDIT = "ADMIN";

    private OrderRepository repository;
    private OrderDtoConverter converter;
    private OrderStatusDtoConverter statusConverter;
    private UserService userService;
    private AddressService addressService;
    private OrderDetailService orderDetailService;
    private OrderStatusService orderStatusService;

    @Autowired
    public OrderService(OrderRepository repository, OrderDtoConverter converter, OrderStatusDtoConverter statusConverter, UserService userService, AddressService addressService, OrderDetailService orderDetailService, OrderStatusService orderStatusService) {
        this.repository = repository;
        this.converter = converter;
        this.statusConverter = statusConverter;
        this.userService = userService;
        this.addressService = addressService;
        this.orderDetailService = orderDetailService;
        this.orderStatusService = orderStatusService;
    }

    public OrderDto save(OrderDto orderDto, String username) {
        Order order = new Order();
        if (orderDto.getId() == null) {
            Address address = addressService.getPersistence(orderDto.getAddress().getId());
            isOwnerAccess(username, address.getUser());
            order.setAddress(address);
            order.setCreationDateTime(LocalDateTime.now());
            BigDecimal totalPrice = new BigDecimal(0);
            for (OrderDetailDto orderDetail : orderDto.getDetails()) {
                if (orderDetail.getPrice()!=null){
                    totalPrice = totalPrice.add(orderDetail.getPrice().multiply(new BigDecimal(orderDetail.getCount())));
                }
            }
            order.setTotalPrice(totalPrice);

            order = repository.save(order);
            for (OrderDetailDto orderDetail : orderDto.getDetails()) {
                or
            }


        } else {
            throw new UnsupportedOperationException();
        }
    }

    public OrderDto addStatus(OrderStatusDto orderStatusDto, Long id, String username){
        isAdminAccess(username);
        Order order = getPersistence(id);
        sortingStatusList(order);
        order.getStatusList().add(statusConverter.convertFromDto(orderStatusDto));
        order = repository.save(order);
        sortingStatusList(order);
        return converter.convertToDto(order);
    }

    public OrderDto find(Long id, String username) {
        Order order = getPersistence(id);
        checkAccess(username, order.getAddress().getUser());
        sortingStatusList(order);
        return converter.convertToDto(order);
    }

    public OrderDto cancel(Long id, String username) {
        Order order = getPersistence(id);
        checkAccess(username, order.getAddress().getUser());
        order.getStatusList().add(createStatusCancelled(order));
        order = repository.save(order);
        sortingStatusList(order);
        return converter.convertToDto(order);
    }


//    public Page<OrderDto> findOrdersByBookId(String username, Long bookId, PageableDto pageableDto) {
//        isAdminAccess(username);
//        Pageable pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize(), pageableDto.getDirection(), pageableDto.getSort());
//        return converter.convertToDtoPage(repository.findOrdersByBookId(bookId, pageable));
//    }
//
//    public Page<OrderDto> findOrdersByStatusAndUsername(String username, Order.Status status, PageableDto pageableDto) {
//        Pageable pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize(), pageableDto.getDirection(), pageableDto.getSort());
//        return converter.convertToDtoPage(repository.findOrdersByStatusAndUserUsername(status, username, pageable));
//    }
//
//    public Page<OrderDto> findOrdersByStatus(String username, Order.Status status, PageableDto pageableDto) {
//        isAdminAccess(username);
//        Pageable pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize(), pageableDto.getDirection(), pageableDto.getSort());
//        return converter.convertToDtoPage(repository.findOrdersByStatus(status, pageable));
//    }

    public Page<OrderDto> findOrdersByUsername(String username, PageableDto pageableDto) {
        Pageable pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize(), pageableDto.getDirection(), pageableDto.getSort());
        Page<Order> orders = repository.findOrdersByAddressUserUsername(username, pageable);
        sortingStatusList(orders);
        return converter.convertToDtoPage(orders);
    }

//    public Page<OrderDto> findOrdersByUserId(String username, Long userId, PageableDto pageableDto) {
//        isAdminAccess(username);
//        Pageable pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize(), pageableDto.getDirection(), pageableDto.getSort());
//        return converter.convertToDtoPage(repository.findOrdersByUserId(userId, pageable));
//    }

    public Page<OrderDto> findAll(String username, PageableDto pageableDto) {
        isAdminAccess(username);
        Pageable pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize(), pageableDto.getDirection(), pageableDto.getSort());
        Page<Order> orders = repository.findAll(pageable);
        sortingStatusList(orders);
        return converter.convertToDtoPage(orders);
    }

    public Order getPersistence(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR));
    }

    private void sortingStatusList(Page<Order> page) {
        for (Order order: page){
            order.setStatusList(order
                    .getStatusList()
                    .stream()
                    .sorted(Comparator.comparing(OrderStatus::getDateTime))
                    .collect(Collectors.toList()));
        }
    }

    private void sortingStatusList(Order order) {
        order.setStatusList(order
                .getStatusList()
                .stream()
                .sorted(Comparator.comparing(OrderStatus::getDateTime))
                .collect(Collectors.toList()));
    }

    private OrderStatus createStatusNew(Order order) {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setDateTime(LocalDateTime.now());
        orderStatus.setStatus(OrderStatus.Status.NEW);
        orderStatus.setOrder(order);
        return orderStatus;
    }

    private OrderStatus createStatusCancelled(Order order) {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setDateTime(LocalDateTime.now());
        orderStatus.setStatus(OrderStatus.Status.CANCELLED);
        orderStatus.setOrder(order);
        return orderStatus;
    }

    private boolean isOwnerAccess(String username, User orderUser) {
        if (!username.equals(orderUser.getUsername())) {
            return false;
        }
        return true;
    }

    private boolean isAdminAccess(String username) {
//        User userDto = userService.getPersistant(username);
//        Role role = userService.findRole(ROLE_FOR_ORDER_EDIT);
        if (!userService
                .getPersistant(username)
                .getAuthorities()
                .contains(userService.findRole(ROLE_FOR_ORDER_EDIT))) {
            return false;
        }
        return true;
    }


    private boolean checkAccess(String username, User orderUser) {
        if (isOwnerAccess(username, orderUser) || isAdminAccess(username)) {
            return true;
        } else {
            throw new AccessDeniedException();
        }
    }
}
