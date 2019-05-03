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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "OrderStatus")
@Table(name = "order_status", schema = "public")
@SequenceGenerator(name = "id_generator", sequenceName = "order_status_sequence", allocationSize = 1)
@AttributeOverride(name = "id", column = @Column(name = "order_status_id"))
@DynamicUpdate
public class OrderStatus extends BaseEntity {

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "order_status", nullable = false)
    private Status status;

    @Column(name = "order_status_date_time", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "order_status_comment", length = 500)
    private String comment;


    public enum Status{
        COMPLETED, NEW, IN_PROCESS, CANCELLED
    }

}
