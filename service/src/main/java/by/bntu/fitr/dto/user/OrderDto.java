package by.bntu.fitr.dto.user;

import by.bntu.fitr.model.book.Book;
import by.bntu.fitr.model.user.Order;
import by.bntu.fitr.model.user.UserData;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDto {

    private Long id;
    private Book book;
    private UserData user;

    private Order.Status status;

    private LocalDateTime dateTime;

    private LocalDateTime modification;

    private String comment;

}
