package by.bntu.fitr.service.user.order;

import by.bntu.fitr.NotFoundException;
import by.bntu.fitr.UnsupportedOperationException;
import by.bntu.fitr.converter.user.OrderDetailDtoConverter;
import by.bntu.fitr.dto.user.order.OrderDetailDto;
import by.bntu.fitr.model.user.order.Order;
import by.bntu.fitr.model.user.order.OrderDetail;
import by.bntu.fitr.repository.user.OrderDetailRepository;
import by.bntu.fitr.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OrderDetailService {

    private static final String NOT_FOUND_ERROR = "exception.not_found.order";

    private OrderDetailRepository repository;
    private OrderDetailDtoConverter converter;
    private BookService bookService;

    @Autowired
    public OrderDetailService(OrderDetailRepository repository, OrderDetailDtoConverter converter, BookService bookService) {
        this.repository = repository;
        this.converter = converter;
        this.bookService = bookService;
    }

    public OrderDetailDto save(OrderDetailDto orderDetailDto, Order order) {
        OrderDetail orderDetail = converter.convertFromDto(orderDetailDto);
        orderDetail.setOrder(order);
        if (orderDetail.getId() == null || !repository.existsById(orderDetail.getId())) {
            orderDetail.setId(null);
            orderDetail.setBook(bookService.getPersistence(orderDetail.getBook().getId()));
            orderDetail.setPrice(orderDetail.getBook().getPrice());
            return converter.convertToDto(repository.save(orderDetail));
        } else {
            throw new UnsupportedOperationException();
        }
    }


    public OrderDetailDto find(Long id) {
        return converter.convertToDto(getPersistence(id));
    }

    public void remove(Long id) {
        repository.deleteById(id);
    }

    public Set<OrderDetailDto> findAll(Long orderId) {
        return converter.convertToDtoSet(repository.findOrderDetailsByOrderId(orderId));
    }

    public OrderDetail getPersistence(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR));
    }

}
