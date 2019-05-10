package by.bntu.fitr.service.user.order;

import by.bntu.fitr.AccessDeniedException;
import by.bntu.fitr.NotFoundException;
import by.bntu.fitr.UnsupportedOperationException;
import by.bntu.fitr.converter.user.OrderDtoConverter;
import by.bntu.fitr.converter.user.OrderStatusDtoConverter;
import by.bntu.fitr.dto.PageableDto;
import by.bntu.fitr.dto.user.order.OrderDetailDto;
import by.bntu.fitr.dto.user.order.OrderDto;
import by.bntu.fitr.dto.user.order.OrderStatusDto;
import by.bntu.fitr.model.user.order.Order;
import by.bntu.fitr.model.user.order.OrderStatus;
import by.bntu.fitr.model.user.util.Address;
import by.bntu.fitr.repository.user.OrderRepository;
import by.bntu.fitr.service.user.UserService;
import by.bntu.fitr.service.user.util.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
            Address address;
            if (orderDto.getAddress().getId()!=null){
                address = addressService.getPersistence(orderDto.getAddress().getId());
            } else {
                address = addressService.getPersistence(
                        addressService.save(orderDto.getAddress(), username).getId());
            }

            if (!username.equals(address.getUser().getUsername())) {
                throw new AccessDeniedException();
            }

            order.setAddress(address);
            order.setCreationDateTime(LocalDateTime.now());
            BigDecimal totalPrice = new BigDecimal(0);
            for (OrderDetailDto orderDetail : orderDto.getDetails()) {
                BigDecimal price = orderDetail.getBook().getPrice();
                if (price!=null){
                    totalPrice = totalPrice.add(price.multiply(new BigDecimal(orderDetail.getCount())));
                }
            }
            order.setTotalPrice(totalPrice);

            order = repository.save(order);
            for (OrderDetailDto orderDetailDto : orderDto.getDetails()) {
                orderDetailService.save(orderDetailDto, order);
            }
            orderStatusService.save(createStatus(OrderStatus.Status.NEW, order));

            return find(order.getId(), username);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public OrderDto addStatus(OrderStatusDto orderStatusDto, Long orderId, String username){
        isAdminAccess(username);
        Order order = getPersistence(orderId);
        OrderStatus orderStatus = statusConverter.convertFromDto(orderStatusDto);
        orderStatus.setOrder(order);
        return find(orderStatus.getOrder().getId(), username);
    }

    public OrderDto find(Long orderId, String username) {
        OrderDto order = converter.convertToDto(getPersistence(orderId));
        checkAccess(username, order);
        order.setDetails(orderDetailService.findAll(order.getId()));
        order.setStatusList(orderStatusService.findAll(order.getId()));
        return order;
    }

    public OrderDto confirmed(Long orderId, String username) {
        OrderDto order = converter.convertToDto(getPersistence(orderId));
        isAdminAccess(username);
        orderStatusService.save(createStatus(OrderStatus.Status.CONFIRMED, converter.convertFromDto(order)));
        return find(orderId, username);
    }

    public OrderDto received(Long orderId, String username) {
        OrderDto order = converter.convertToDto(getPersistence(orderId));
        isOwnerAccess(username, order);
        orderStatusService.save(createStatus(OrderStatus.Status.RECEIVED, converter.convertFromDto(order)));
        return find(orderId, username);
    }

    public OrderDto returned(Long orderId, String username) {
        OrderDto order = converter.convertToDto(getPersistence(orderId));
        isAdminAccess(username);
        orderStatusService.save(createStatus(OrderStatus.Status.RETURNED, converter.convertFromDto(order)));
        return find(orderId, username);
    }

    public OrderDto canceled(Long orderId, String username) {
        OrderDto order = converter.convertToDto(getPersistence(orderId));
        checkAccess(username, order);
        orderStatusService.save(createStatus(OrderStatus.Status.CANCELLED, converter.convertFromDto(order)));
        return find(orderId, username);
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
        Page<OrderDto> pageDto = converter.convertToDtoPage(orders);
        for (OrderDto orderDto : pageDto){
            orderDto.setStatusList(orderStatusService.findAll(orderDto.getId()));
            orderDto.setDetails(orderDetailService.findAll(orderDto.getId()));
        }
        return pageDto;

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
        Page<OrderDto> pageDto = converter.convertToDtoPage(orders);
        for (OrderDto orderDto : pageDto){
            orderDto.setStatusList(orderStatusService.findAll(orderDto.getId()));
            orderDto.setDetails(orderDetailService.findAll(orderDto.getId()));
        }
        return pageDto;
    }

    public Order getPersistence(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR));
    }

    private OrderStatus createStatus(OrderStatus.Status status, Order order) {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setDateTime(LocalDateTime.now());
        orderStatus.setStatus(status);
        orderStatus.setOrder(order);
        return orderStatus;
    }

    private boolean isOwnerAccess(String username, OrderDto orderDto) {
        if (!username.equals(orderDto.getAddress().getUser().getUsername())) {
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


    private boolean checkAccess(String username, OrderDto orderDto) {
        if (isOwnerAccess(username, orderDto) || isAdminAccess(username)) {
            return true;
        } else {
            throw new AccessDeniedException();
        }
    }
}
