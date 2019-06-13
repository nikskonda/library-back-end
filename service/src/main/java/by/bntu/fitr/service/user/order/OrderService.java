package by.bntu.fitr.service.user.order;

import by.bntu.fitr.AccessDeniedException;
import by.bntu.fitr.NotFoundException;
import by.bntu.fitr.UnsupportedOperationException;
import by.bntu.fitr.config.UserRole;
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
import by.bntu.fitr.model.user.order.UserOrder;
import by.bntu.fitr.repository.user.order.OrderRepository;
import by.bntu.fitr.service.user.UserService;
import by.bntu.fitr.service.user.util.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {

    private static final String NOT_FOUND_ERROR = "exception.notFound.order";

    private OrderRepository repository;
    private OrderDtoConverter converter;
    private OrderStatusDtoConverter statusConverter;
    private OrderDetailDtoConverter detailConverter;
    private UserService userService;
    private AddressService addressService;
    private UserRole userRole;

    @Autowired
    public OrderService(OrderRepository repository, OrderDtoConverter converter, OrderStatusDtoConverter statusConverter, OrderDetailDtoConverter detailConverter, UserService userService, AddressService addressService, UserRole userRole) {
        this.repository = repository;
        this.converter = converter;
        this.statusConverter = statusConverter;
        this.detailConverter = detailConverter;
        this.userService = userService;
        this.addressService = addressService;
        this.userRole = userRole;
    }

    public OrderDto save(OrderDto orderDto, String username) {
        Order order = new Order();
        if (orderDto.getId() == null) {
            UserOrder userOrder = new UserOrder();
            if (orderDto.getAddress().getId() != null) {
                userOrder.setAddress(addressService.getPersistence(orderDto.getAddress().getId()));
            } else {
                userOrder.setAddress(addressService.getPersistence(addressService.save(orderDto.getAddress()).getId()));
            }
            userOrder.setUser(userService.getPersistence(username));
            order.setUserOrder(userOrder);

            order.setCreationDateTime(LocalDateTime.now());
            order.addStatus(statusConverter.convertFromDto(createStatus(OrderStatus.Status.NEW)));

            for (OrderDetailDto orderDetailDto : orderDto.getDetails()) {
                boolean flag = true;
                if (order.getDetails() != null) {
                    for (OrderDetail orderDetail : order.getDetails()) {
                        if (orderDetail.getBook().getId().equals(orderDetailDto.getBook().getId())) {
                            orderDetail.setCount(orderDetail.getCount() + orderDetailDto.getCount());
                            flag = false;
                            break;
                        }
                    }
                }
                if (flag) {
                    order.addDetail(detailConverter.convertFromDto(orderDetailDto));
                }

            }
            return converter.convertToDto(repository.save(order));
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public OrderDto handBook(OrderDto orderDto) {
        if (this.isContainInLibraryReadBookOnly(orderDto)) {
            OrderDto inLibraryReadBookOnlyOrder = new OrderDto();
            Set<OrderDetailDto> detailSet = new HashSet<>();
            for (OrderDetailDto detail : orderDto.getDetails()) {
                if (detail.getBook().isInLibraryUseOnly()) {
                    detailSet.add(detail);
                }
            }
            orderDto.getDetails().removeAll(detailSet);
            inLibraryReadBookOnlyOrder.setDetails(detailSet);

            inLibraryReadBookOnlyOrder = this.saveOrderWithoutAddress(inLibraryReadBookOnlyOrder, orderDto.getUser().getUsername());
            addStatus(new OrderStatusDto(OrderStatus.Status.HANDED_OUT), inLibraryReadBookOnlyOrder.getId(), orderDto.getUser().getUsername());
        }
        if (!orderDto.getDetails().isEmpty()){
            orderDto = this.saveOrderOnRegistrationAddress(orderDto, orderDto.getUser().getUsername());
            addStatus(new OrderStatusDto(OrderStatus.Status.HANDED_OUT), orderDto.getId(), orderDto.getUser().getUsername());
        }
        return null;
    }

    private boolean isContainInLibraryReadBookOnly(OrderDto order) {
        for (OrderDetailDto detail : order.getDetails()) {
            if (detail.getBook().isInLibraryUseOnly()) {
                return true;
            }
        }
        return false;
    }

    public OrderDto saveOrderOnRegistrationAddress(OrderDto orderDto, String username) {
        Order order = new Order();
        if (orderDto.getId() == null) {
            UserOrder userOrder = new UserOrder();
            userOrder.setUser(userService.getPersistence(username));
            userOrder.setAddress(userOrder.getUser().getRegistrationAddress());

            order.setUserOrder(userOrder);

            order.setCreationDateTime(LocalDateTime.now());
            order.addStatus(statusConverter.convertFromDto(createStatus(OrderStatus.Status.NEW)));

            for (OrderDetailDto orderDetailDto : orderDto.getDetails()) {
                boolean flag = true;
                if (order.getDetails() != null) {
                    for (OrderDetail orderDetail : order.getDetails()) {
                        if (orderDetail.getBook().getId().equals(orderDetailDto.getBook().getId())) {
                            orderDetail.setCount(orderDetail.getCount() + orderDetailDto.getCount());
                            flag = false;
                            break;
                        }
                    }
                }
                if (flag) {
                    order.addDetail(detailConverter.convertFromDto(orderDetailDto));
                }

            }
            return converter.convertToDto(repository.save(order));
        } else {
            throw new UnsupportedOperationException();
        }
    }

    private OrderDto saveOrderWithoutAddress(OrderDto orderDto, String username) {
        Order order = new Order();
        if (orderDto.getId() == null) {
            UserOrder userOrder = new UserOrder();
            userOrder.setUser(userService.getPersistence(username));
            order.setUserOrder(userOrder);

            order.setCreationDateTime(LocalDateTime.now());
            order.addStatus(statusConverter.convertFromDto(createStatus(OrderStatus.Status.NEW)));

            for (OrderDetailDto orderDetailDto : orderDto.getDetails()) {
                boolean flag = true;
                if (order.getDetails() != null) {
                    for (OrderDetail orderDetail : order.getDetails()) {
                        if (orderDetail.getBook().getId().equals(orderDetailDto.getBook().getId())) {
                            orderDetail.setCount(orderDetail.getCount() + orderDetailDto.getCount());
                            flag = false;
                            break;
                        }
                    }
                }
                if (flag) {
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
        orderStatusDto.setDateTime(LocalDateTime.now());
        checkAccess(username, order.getUserOrder().getUser().getUsername());
        order.addStatus(statusConverter.convertFromDto(orderStatusDto));
        return converter.convertToDto(repository.save(order));
    }

    public OrderDto find(Long orderId, String username) {
        Order order = getPersistence(orderId);
        checkAccess(username, order.getUserOrder().getUser().getUsername());
        return converter.convertToDto(order);
    }

    public OrderDto confirmed(Long orderId, String username) {
        return this.addStatus(createStatus(OrderStatus.Status.CONFIRMED), orderId, username);
    }

    public OrderDto received(Long orderId, String username) {
        return this.addStatus(createStatus(OrderStatus.Status.RECEIVED), orderId, username);
    }

    public OrderDto returned(Long orderId, String username) {
        return this.addStatus(createStatus(OrderStatus.Status.RETURNED), orderId, username);
    }

    public OrderDto canceled(Long orderId, String username) {
        return this.addStatus(createStatus(OrderStatus.Status.CANCELLED), orderId, username);
    }


    public Page<OrderDto> findOrdersByUsername(String username, OrderStatus.Status status, PageableDto pageableDto) {
        Pageable pageable;
        if (status == null) {
            pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize(), pageableDto.getDirection(), pageableDto.getSort());
            Page<OrderDto> pageDto = converter.convertToDtoPage(repository.findOrdersByAddressUserUsername(username, pageable));
            return pageDto;
        } else {
            Long userId = userService.getPersistence(username).getId();
            return findOrdersByUserId(userId, status, pageableDto);
        }
    }

    public Page<OrderDto> findOrdersByUserId(Long userId, OrderStatus.Status status, PageableDto pageableDto) {
        Pageable pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize(), pageableDto.getDirection(), pageableDto.getSort());
        if (status == null) {

            Page<Order> page = repository.findOrdersByAddressUserId(userId, pageable);
            Page<OrderDto> pageDto = converter.convertToDtoPage(page);
            return pageDto;
        } else {
            List<Long> idList = repository.findOrdersByStatusAndUserId(
                    OrderStatus.Status.valueOf(status.toString()).ordinal(),
                    userId,
                    pageableDto.getSize(),
                    pageableDto.getNumber() * pageableDto.getSize()
            );
            List<OrderDto> list = converter.convertToDtoList(repository.findOrdersByIdIn(idList, pageable));
            Page<OrderDto> page = new PageImpl<>(list, pageable, repository.countOrdersByStatusAndUserId(OrderStatus.Status.valueOf(status.toString()).ordinal(), userId));
            return page;
        }
    }

    public Page<OrderDto> findOrdersByBookId(Long bookId, OrderStatus.Status status, PageableDto pageableDto) {
        Pageable pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize(), pageableDto.getDirection(), pageableDto.getSort());
        if (status == null) {
            Page<Order> page = repository.findByDetails_BookId(bookId, pageable);
            Page<OrderDto> pageDto = converter.convertToDtoPage(page);
            return pageDto;
        } else {
            List<Long> idList = repository.findOrdersByStatusAndBookId(
                    OrderStatus.Status.valueOf(status.toString()).ordinal(),
                    bookId,
                    pageableDto.getSize(),
                    pageableDto.getNumber() * pageableDto.getSize()
            );
            List<OrderDto> list = converter.convertToDtoList(repository.findOrdersByIdIn(idList, pageable));
            Page<OrderDto> page = new PageImpl<>(list, pageable, repository.countOrdersByStatusAndBookId(OrderStatus.Status.valueOf(status.toString()).ordinal(), bookId));
            return page;
        }
    }

    public Page<OrderDto> findAll(String username, OrderStatus.Status status, PageableDto pageableDto) {
        Pageable pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize(), pageableDto.getDirection(), pageableDto.getSort());
        if (status == null) {
            Page<Order> page = repository.findAll(pageable);
            Page<OrderDto> pageDto = converter.convertToDtoPage(page);
            return pageDto;
        } else {
//            pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize());
            List<Long> idList = repository.findOrdersByStatus(
                    OrderStatus.Status.valueOf(status.toString()).ordinal(),
                    pageableDto.getSize(),
                    pageableDto.getNumber() * pageableDto.getSize()
            );
            List<OrderDto> list = converter.convertToDtoList(repository.findOrdersByIdIn(idList, pageable));
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

    private boolean isOwnerAccess(String username, String orderUsername) {
        return username.equals(orderUsername);
    }

    private boolean isAdminAccess(String username) {
        return userService
                .getPersistence(username)
                .getAuthorities()
                .contains(userService.findRole(userRole.getOperator()));
    }


    private boolean checkAccess(String username, String orderUsername) {
        if (isOwnerAccess(username, orderUsername) || isAdminAccess(username)) {
            return true;
        } else {
            throw new AccessDeniedException();
        }
    }
}
