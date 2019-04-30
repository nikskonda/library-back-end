package by.bntu.fitr.dto.user.order;

import by.bntu.fitr.dto.book.BookDto;
import by.bntu.fitr.model.BaseEntity;
import by.bntu.fitr.model.book.Book;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
public class OrderDetailDto {

    private Long id;

    private BookDto book;

    private BigDecimal price;

    private String comment;

    private Integer count;
}
