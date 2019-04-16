package by.bntu.fitr.service.user;

import by.bntu.fitr.AccessDeniedException;
import by.bntu.fitr.NotFoundException;
import by.bntu.fitr.converter.user.OrderDtoConverter;
import by.bntu.fitr.dto.PageableDto;
import by.bntu.fitr.dto.user.OrderDto;
import by.bntu.fitr.dto.user.RoleDto;
import by.bntu.fitr.dto.user.UserDto;
import by.bntu.fitr.model.user.Order;
import by.bntu.fitr.model.user.Role;
import by.bntu.fitr.model.user.User;
import by.bntu.fitr.repository.user.OrderRepository;
import by.bntu.fitr.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Service
public class OrderService {

    private static final String NOT_FOUND_ERROR = "exception.not_found.author";
    private static final String ROLE_FOR_ORDER_EDIT = "ADMIN";

    private OrderRepository repository;
    private OrderDtoConverter converter;
    private UserService userService;
    private BookService bookService;

    @Autowired
    public OrderService(OrderRepository repository, OrderDtoConverter converter, UserService userService, BookService bookService) {
        this.repository = repository;
        this.converter = converter;
        this.userService = userService;
        this.bookService = bookService;
    }

    public OrderDto save(OrderDto orderDto, String username){
        Order order;
        if (orderDto.getId()==null){
            orderDto.setBook(bookService.find(orderDto.getBook().getId()));
            order = converter.convertFromDto(orderDto);
            order.setUser(userService.getPersistant(username));
            order.setModificationDateTime(null);
            order.setCreationDateTime(LocalDateTime.now());
            order.setStatus(Order.Status.NEW);
        } else {
            order = getPersist(orderDto.getId());

            checkAccess(username, order.getUser());

            order.setModificationDateTime(LocalDateTime.now());
            if (isOwnerAccess(username, order.getUser()) && !StringUtils.isEmpty(orderDto.getComment())){
                order.setComment(orderDto.getComment());
            }
            if (isAdminAccess(username) && orderDto.getStatus()!=null){
                order.setStatus(orderDto.getStatus());
            }
        }

        order.setUser(userService.getPersistant(username));
        return converter.convertToDto(repository.save(order));
    }

    public OrderDto find(Long id, String username){
        Order order = getPersist(id);
        checkAccess(username, order.getUser());
        return converter.convertToDto(order);
    }

    public Page<OrderDto> findOrdersByBookId(String username, Long bookId, PageableDto pageableDto){
        isAdminAccess(username);
        Pageable pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize(), pageableDto.getDirection(), pageableDto.getSort());
        return converter.convertToDtoPage(repository.findOrdersByBookId(bookId, pageable));
    }

    public Page<OrderDto> findOrdersByStatusAndUsername(String username, Order.Status status, PageableDto pageableDto){
        Pageable pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize(), pageableDto.getDirection(), pageableDto.getSort());
        return converter.convertToDtoPage(repository.findOrdersByStatusAndUserUsername(status, username, pageable));
    }

    public Page<OrderDto> findOrdersByStatus(String username, Order.Status status, PageableDto pageableDto){
        isAdminAccess(username);
        Pageable pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize(), pageableDto.getDirection(), pageableDto.getSort());
        return converter.convertToDtoPage(repository.findOrdersByStatus(status, pageable));
    }

    public Page<OrderDto> findOrdersByUsername(String username, PageableDto pageableDto){
        Pageable pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize(), pageableDto.getDirection(), pageableDto.getSort());
        return converter.convertToDtoPage(repository.findOrdersByUserUsername(username, pageable));
    }

    public Page<OrderDto> findOrdersByUserId(String username, Long userId, PageableDto pageableDto){
        isAdminAccess(username);
        Pageable pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize(), pageableDto.getDirection(), pageableDto.getSort());
        return converter.convertToDtoPage(repository.findOrdersByUserId(userId, pageable));
    }

    public Page<OrderDto> findAll(PageableDto pageableDto){
        Pageable pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize(), pageableDto.getDirection(), pageableDto.getSort());
        return converter.convertToDtoPage(repository.findAll(pageable));
    }

    public Order getPersist(Long id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR));
    }


    private boolean isOwnerAccess(String username, User orderUser){
        if (!username.equals(orderUser.getUsername())){
            return false;
        }
        return true;
    }

    private boolean isAdminAccess(String username){
//        User userDto = userService.getPersistant(username);
//        Role role = userService.findRole(ROLE_FOR_ORDER_EDIT);
        if (!userService
                        .getPersistant(username)
                        .getAuthorities()
                        .contains(userService.findRole(ROLE_FOR_ORDER_EDIT))){
            return false;
        }
        return true;
    }


    private boolean checkAccess(String username, User orderUser){
        if (isOwnerAccess(username, orderUser) || isAdminAccess(username)){
            return true;
        } else {
            throw new AccessDeniedException();
        }
    }
}
