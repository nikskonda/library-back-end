package by.bntu.fitr.model.user.order;

import by.bntu.fitr.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "OrderStatus")
@Table(name = "order_status", schema = "public")
@SequenceGenerator(name = "id_generator", sequenceName = "order_status_sequence", allocationSize = 1)
@AttributeOverride(name = "id", column = @Column(name = "order_status_id"))
@DynamicUpdate
public class OrderStatus extends BaseEntity {

    @ManyToOne(cascade = {CascadeType.MERGE }, fetch=FetchType.LAZY)
    @JoinColumn(name = "order_id")
//    @JsonBackReference
    private Order order;

    @Column(name = "order_status", nullable = false)
    private Status status;

    @Column(name = "order_status_date_time", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "order_status_comment", length = 500)
    private String comment;


    public enum Status{
        NEW, CONFIRMED, HANDED_OUT, AT_COURIER,  RECEIVED, RETURN_TO_COURIER, RETURNED, CANCELLED
    }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "status=" + status +
                ", dateTime=" + dateTime +
                ", comment='" + comment + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderStatus that = (OrderStatus) o;
        return status == that.status &&
                Objects.equals(dateTime, that.dateTime) &&
                Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), status, dateTime, comment);
    }
}
