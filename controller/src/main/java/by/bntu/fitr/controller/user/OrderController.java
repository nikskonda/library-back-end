package by.bntu.fitr.controller.user;

import by.bntu.fitr.dto.PageableDto;
import by.bntu.fitr.dto.user.order.OrderDto;
import by.bntu.fitr.dto.user.order.OrderStatusDto;
import by.bntu.fitr.model.user.order.OrderStatus;
import by.bntu.fitr.service.user.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Validated
@RestController
@CrossOrigin
@RequestMapping(value = "/order")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public OrderDto find(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id,
                         Authentication authentication) {
        return orderService.find(id, authentication.getName());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto create(@Valid @RequestBody OrderDto orderDto,
                           Authentication authentication) {
        return orderService.save(orderDto, authentication.getName());
    }

    @PostMapping("/{id}/status")
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    public OrderDto addStatus(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id,
                              @Valid @RequestBody OrderStatusDto orderStatusDto,
                              Authentication authentication) {
        return orderService.addStatus(orderStatusDto, id, authentication.getName());
    }

    @PostMapping("/{id}/confirmed")
    @PreAuthorize("hasAuthority('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto confirmed(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id,
                              Authentication authentication) {
        return orderService.confirmed(id, authentication.getName());
    }

    @PostMapping("/{id}/received")
    @PreAuthorize("hasAuthority('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto received(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id,
                             Authentication authentication) {
        return orderService.received(id, authentication.getName());
    }

    @PostMapping("/{id}/returned")
    @PreAuthorize("hasAuthority('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto returned(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id,
                             Authentication authentication) {
        return orderService.returned(id, authentication.getName());
    }

    @PostMapping("/{id}/canceled")
    @PreAuthorize("hasAuthority('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto canceled(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id,
                             Authentication authentication) {
        return orderService.canceled(id, authentication.getName());
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('USER')")
    public Page<OrderDto> findByUsername(Authentication authentication,
                                         OrderStatus.Status status,
                                         PageableDto pageableDto) {
        return orderService.findOrdersByUsername(authentication.getName(), status, pageableDto);
    }

//    @GetMapping("/user/{id}")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public Page<OrderDto> findByUserId(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id,
//                                       OrderStatus.Status status,
//                                       Authentication authentication,
//                                       PageableDto pageableDto) {
//        return orderService.findOrdersByUserId(authentication.getName(), id, pageableDto);
//    }
//
//    @GetMapping("/book/{id}")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public Page<OrderDto> findByBookId(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id,
//                                       Authentication authentication,
//                                       PageableDto pageableDto) {
//        return orderService.findOrdersByBookId(authentication.getName(), id, pageableDto);
//    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<OrderDto> findByPage(Authentication authentication, OrderStatus.Status status, PageableDto pageableDto) {
        return orderService.findAll(authentication.getName(), status, pageableDto);
    }

}
