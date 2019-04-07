package by.bntu.fitr.controller.user;

import by.bntu.fitr.dto.user.OrderDto;
import by.bntu.fitr.model.user.Order;
import by.bntu.fitr.service.user.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Set;

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
    public OrderDto find(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id) {
        return orderService.find(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto create(@Valid @RequestBody OrderDto orderDto) {
        return orderService.save(orderDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    public OrderDto update(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id,
                          @Validated() @RequestBody OrderDto orderDto) {
        orderDto.setId(id);
        return orderService.save(orderDto);
    }


    @GetMapping("/user/{id}")
    public Set<OrderDto> findByUserId(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id) {
        return orderService.findOrdersByUserId(id);
    }

    @GetMapping("/book/{id}")
    public Set<OrderDto> findByBookId(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id) {
        return orderService.findOrdersByBookId(id);
    }

    @GetMapping
    public Set<OrderDto> findByUserId(Order.Status status) {
        return orderService.findOrdersByStatus(status);
    }

}
