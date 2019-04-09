package by.bntu.fitr.model.user;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "Order")
@Table(name = "order", schema = "public")
@SequenceGenerator(name = "id_generator", sequenceName = "order_sequence", allocationSize = 1)
@AttributeOverride(name = "id", column = @Column(name = "order_id"))
@DynamicUpdate
public class Order extends BaseEntity {

    @ManyToOne(cascade = {CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(cascade = {CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserData user;

    @Column(name = "order_status", nullable = false)
    private Status status;

    @Column(name = "order_date_time", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "order_modification")
    private LocalDateTime modification;

    @Column(name = "order_comment", length = 500)
    private String comment;


    public enum Status{
        COMPLETED, NEW, IN_PROCESS
    }

}
