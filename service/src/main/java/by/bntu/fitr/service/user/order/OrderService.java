package by.bntu.fitr.service.user.order;

import by.bntu.fitr.AccessDeniedException;
import by.bntu.fitr.NotFoundException;
import by.bntu.fitr.UnsupportedOperationException;
import by.bntu.fitr.converter.user.OrderDetailDtoConverter;
import by.bntu.fitr.converter.user.OrderDtoConverter;
import by.bntu.fitr.converter.user.OrderStatusDtoConverter;
import by.bntu.fitr.dto.PageableDto;
import by.bntu.fitr.dto.user.order.OrderDetailDto;
import by.bntu.fitr.dto.user.order.OrderDto;
import by.bntu.fitr.dto.user.order.OrderStatusDto;
import by.bntu.fitr.model.user.order.Order;
import by.bntu.fitr.model.user.order.OrderDetail;
import by.bntu.fitr.model.user.order.OrderStatus;
import by.bntu.fitr.model.user.util.Address;
import by.bntu.fitr.repository.user.OrderRepository;
import by.bntu.fitr.service.user.UserService;
import by.bntu.fitr.service.user.util.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private static final String NOT_FOUND_ERROR = "exception.not_found.order";
    private static final String ROLE_FOR_ORDER_EDIT = "ADMIN";

    private OrderRepository repository;
    private OrderDtoConverter converter;
    private OrderStatusDtoConverter statusConverter;
    private OrderDetailDtoConverter detailConverter;
    private UserService userService;
    private AddressService addressService;

    @Autowired
    public OrderService(OrderRepository repository, OrderDtoConverter converter, OrderStatusDtoConverter statusConverter, OrderDetailDtoConverter detailConverter, UserService userService, AddressService addressService) {
        this.repository = repository;
        this.converter = converter;
        this.statusConverter = statusConverter;
        this.detailConverter = detailConverter;
        this.userService = userService;
        this.addressService = addressService;
    }

    public OrderDto save(OrderDto orderDto, String username) {
        Order order = new Order();
        if (orderDto.getId() == null) {
            Address address;
            if (orderDto.getAddress().getId() != null) {
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
            order.addStatus(statusConverter.convertFromDto(createStatus(OrderStatus.Status.NEW)));

            for (OrderDetailDto orderDetailDto : orderDto.getDetails()) {
                boolean flag = true;
                if (order.getDetails()!=null){
                    for (OrderDetail orderDetail: order.getDetails()){
                        if (orderDetail.getBook().getId().equals(orderDetailDto.getBook().getId())){
                            orderDetail.setCount(orderDetail.getCount()+orderDetailDto.getCount());
                            flag=false;
                            break;
                        }
                    }
                }
                if (flag){
                    order.addDetail(detailConverter.convertFromDto(orderDetailDto));
                }

            }
            return converter.convertToDto(repository.save(order));
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public OrderDto addStatus(OrderStatusDto orderStatusDto, Long orderId, String username) {
        Order order = getPersistence(orderId);
        checkAccess(username, converter.convertToDto(order));
        order.addStatus(statusConverter.convertFromDto(orderStatusDto));
        return converter.convertToDto(repository.save(order));
    }

    public OrderDto find(Long orderId, String username) {
        OrderDto order = converter.convertToDto(getPersistence(orderId));
        checkAccess(username, order);
        return order;
    }

    public OrderDto confirmed(Long orderId, String username) {
        if (isAdminAccess(username)) {
            return this.addStatus(createStatus(OrderStatus.Status.CONFIRMED), orderId, username);
        }
        throw new AccessDeniedException();
    }

    public OrderDto received(Long orderId, String username) {
        if (isOwnerAccess(username, find(orderId, username))) {
            return this.addStatus(createStatus(OrderStatus.Status.RECEIVED), orderId, username);
        }
        throw new AccessDeniedException();
    }

    public OrderDto returned(Long orderId, String username) {
        if (isAdminAccess(username)) {
            return this.addStatus(createStatus(OrderStatus.Status.RETURNED), orderId, username);
        }
        throw new AccessDeniedException();
    }

    public OrderDto canceled(Long orderId, String username) {
        return this.addStatus(createStatus(OrderStatus.Status.CANCELLED), orderId, username);
    }


    public Page<OrderDto> findOrdersByUsername(String username, OrderStatus.Status status, PageableDto pageableDto) {
        Pageable pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize(), pageableDto.getDirection(), pageableDto.getSort());
        Page<OrderDto> pageDto = converter.convertToDtoPage(repository.findOrdersByAddressUserUsername(username, pageable));
        return pageDto;

    }

    public Page<OrderDto> findOrdersByUserId(Long userId, OrderStatus.Status status, PageableDto pageableDto) {
        Pageable pageable;
        if (status == null) {
            pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize(), pageableDto.getDirection(), pageableDto.getSort());
            Page<Order> page = repository.findOrdersByAddressUserId(userId, pageable);
            Page<OrderDto> pageDto = converter.convertToDtoPage(page);
            return pageDto;
        } else {
            pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize());
            List<Long> idList = repository.findOrdersByStatusAndUserId(
                    OrderStatus.Status.valueOf(status.toString()).ordinal(),
                    userId,
                    pageableDto.getSize(),
                    pageableDto.getNumber() * pageableDto.getSize()
//                    " order_id DESC "
            );
            List<OrderDto> list = converter.convertToDtoList(repository.findOrdersByIdIn(idList));
            Page<OrderDto> page = new PageImpl<>(list, pageable, repository.countOrdersByStatusAndUserId(OrderStatus.Status.valueOf(status.toString()).ordinal(), userId));
            return page;
        }
    }

    public Page<OrderDto> findOrdersByBookId(Long bookId, OrderStatus.Status status, PageableDto pageableDto) {
        Pageable pageable;
        if (status == null) {
            pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize(), pageableDto.getDirection(), pageableDto.getSort());
            Page<Order> page = repository.findByDetails_BookId(bookId, pageable);
            Page<OrderDto> pageDto = converter.convertToDtoPage(page);
            return pageDto;
        } else {
            pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize());
            List<Long> idList = repository.findOrdersByStatusAndBookId(
                    OrderStatus.Status.valueOf(status.toString()).ordinal(),
                    bookId,
                    pageableDto.getSize(),
                    pageableDto.getNumber() * pageableDto.getSize()
//                    " DESC "
            );
            List<OrderDto> list = converter.convertToDtoList(repository.findOrdersByIdIn(idList));
            Page<OrderDto> page = new PageImpl<>(list, pageable, repository.countOrdersByStatusAndBookId(OrderStatus.Status.valueOf(status.toString()).ordinal(), bookId));
            return page;
        }
    }

    public Page<OrderDto> findAll(String username, OrderStatus.Status status, PageableDto pageableDto) {
        isAdminAccess(username);
        Pageable pageable;
        if (status == null) {
            pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize(), pageableDto.getDirection(), pageableDto.getSort());
            Page<Order> page = repository.findAll(pageable);
            Page<OrderDto> pageDto = converter.convertToDtoPage(page);
            return pageDto;
        } else {
            pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize());
            List<Long> idList = repository.findOrdersByStatus(
                    OrderStatus.Status.valueOf(status.toString()).ordinal(),
                    pageableDto.getSize(),
                    pageableDto.getNumber() * pageableDto.getSize()
//                    " order_id DESC "
            );
            List<OrderDto> list = converter.convertToDtoList(repository.findOrdersByIdIn(idList));
            Page<OrderDto> page = new PageImpl<>(list, pageable, repository.countOrdersByStatus(OrderStatus.Status.valueOf(status.toString()).ordinal()));
            return page;
        }

    }

    public Order getPersistence(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR));
    }


    private OrderStatusDto createStatus(OrderStatus.Status status) {
        OrderStatusDto orderStatus = new OrderStatusDto();
        orderStatus.setDateTime(LocalDateTime.now());
        orderStatus.setStatus(status);
        return orderStatus;
    }

    private boolean isOwnerAccess(String username, OrderDto orderDto) {
        if (!username.equals(orderDto.getAddress().getUser().getUsername())) {
            return false;
        }
        return true;
    }

    private boolean isAdminAccess(String username) {
//        User userDto = userService.getPersistence(username);
//        Role role = userService.findRole(ROLE_FOR_ORDER_EDIT);
        if (!userService
                .getPersistence(username)
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
