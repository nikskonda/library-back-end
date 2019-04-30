package by.bntu.fitr.model.user.order;

import by.bntu.fitr.model.BaseEntity;
import by.bntu.fitr.model.book.Book;
import by.bntu.fitr.model.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "OrderDetail")
@Table(name = "order_detail", schema = "public")
@SequenceGenerator(name = "id_generator", sequenceName = "order_detail_sequence", allocationSize = 1)
@AttributeOverride(name = "id", column = @Column(name = "order_detail_id"))
@DynamicUpdate
public class OrderDetail extends BaseEntity {

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(cascade = {CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "order_detail_price", nullable = false)
    private BigDecimal price;

    @Column(name = "order_detail_comment", length = 500)
    private String comment;

    @Column(name = "order_detail_count")
    private Integer count;


}
