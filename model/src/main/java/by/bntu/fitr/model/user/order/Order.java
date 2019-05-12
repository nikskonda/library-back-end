package by.bntu.fitr.model.user.order;

import by.bntu.fitr.model.BaseEntity;
import by.bntu.fitr.model.user.util.Address;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Order")
@Table(name = "order", schema = "public")
@SequenceGenerator(name = "id_generator", sequenceName = "order_sequence", allocationSize = 1)
@AttributeOverride(name = "id", column = @Column(name = "order_id"))
@DynamicUpdate
public class Order extends BaseEntity {

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
//    @JsonManagedReference
    private Set<OrderDetail> details;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
//    @JsonManagedReference
    private List<OrderStatus> statusList;

    @Column(name="order_total_price")
    private BigDecimal totalPrice;

    @ManyToOne(cascade = {CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "order_creation_date_time", nullable = false)
    private LocalDateTime creationDateTime;

    @Override
    public String toString() {
        return "Order{" +
                "details=" + details +
                ", address=" + address +
                ", creationDateTime=" + creationDateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Order order = (Order) o;
        return Objects.equals(totalPrice, order.totalPrice) &&
                Objects.equals(address, order.address) &&
                Objects.equals(creationDateTime, order.creationDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), totalPrice, address, creationDateTime);
    }
}
