package by.bntu.fitr.dto.user;

import by.bntu.fitr.dto.book.BookDto;
import by.bntu.fitr.model.book.Book;
import by.bntu.fitr.model.user.Order;
import by.bntu.fitr.model.user.UserData;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDto {

    private Long id;
    private BookDto book;
    private UserDataDto user;

    private Order.Status status;

    private LocalDateTime creationDateTime;

    private LocalDateTime modificationDateTime;

    private String comment;

}
