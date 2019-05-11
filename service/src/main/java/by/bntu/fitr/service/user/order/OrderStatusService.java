package by.bntu.fitr.service.user.order;

import by.bntu.fitr.NotFoundException;
import by.bntu.fitr.converter.user.OrderStatusDtoConverter;
import by.bntu.fitr.dto.user.order.OrderStatusDto;
import by.bntu.fitr.model.user.order.OrderStatus;
import by.bntu.fitr.repository.user.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderStatusService {

    private static final String NOT_FOUND_ERROR = "exception.not_found.order";

    private OrderStatusRepository repository;
    private OrderStatusDtoConverter converter;

    @Autowired
    public OrderStatusService(OrderStatusRepository repository, OrderStatusDtoConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public OrderStatusDto save(OrderStatus orderStatus) {
        if (orderStatus.getId() == null || !repository.existsById(orderStatus.getId())) {
            orderStatus.setId(null);
            orderStatus.setDateTime(LocalDateTime.now());
        } else {
            OrderStatus oldStatus = getPersistence(orderStatus.getId());
            oldStatus.setComment(orderStatus.getComment());
            orderStatus = oldStatus;
        }
        return converter.convertToDto(repository.save(orderStatus));
    }


    public OrderStatusDto find(Long id) {
        return converter.convertToDto(getPersistence(id));
    }

    public void remove(Long id) {
        repository.deleteById(id);
    }

    public List<OrderStatusDto> findAll(Long orderId) {
        return converter.convertToDtoList(repository.findOrderStatusesByOrderIdOrderByDateTimeAsc(orderId));
    }

    public OrderStatus getPersistence(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR));
    }

    public Page<OrderStatus> findAllByStatus(OrderStatus.Status status, Pageable pageable) {
        return repository.findOrderStatusesByStatus(status, pageable);
    }

}
