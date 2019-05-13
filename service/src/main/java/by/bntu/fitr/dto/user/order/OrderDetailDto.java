package by.bntu.fitr.dto.user.order;

import by.bntu.fitr.dto.book.BookDto;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Data
public class OrderDetailDto {

    @Null(message = "exception.validation.orderDetail.id.null")
    private Long id;

    @NotNull(message = "exception.validation.orderDetail.book.notNull")
    private BookDto book;

//    @Null(message = "exception.validation.orderDetail.price.null")
//    private BigDecimal price;

    @Size(min = 1, max = 500, message = "exception.validation.orderDetail.comment.size")
    private String comment;

    @NotNull(message = "exception.validation.orderDetail.count.notNull")
    @Min(value = 1, message = "exception.validation.orderDetail.count.min")
    private Integer count;
}
