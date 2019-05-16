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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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

    @OneToOne(mappedBy = "order", fetch = FetchType.EAGER, cascade = {CascadeType.ALL })
    private UserOrder userOrder;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade={CascadeType.ALL})
    private Set<OrderDetail> details;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade={CascadeType.ALL})
    private List<OrderStatus> statusList;

    @Column(name="order_total_price")
    private BigDecimal totalPrice;

    @Column(name = "order_creation_date_time", nullable = false)
    private LocalDateTime creationDateTime;

    public void setDetails(Set<OrderDetail> details) {
        details.forEach(detail -> detail.setOrder(this));
        this.details = details;
    }

    public void addDetail(OrderDetail detail){
        if (this.details==null){
            this.details = new HashSet<>();
        }
        detail.setOrder(this);
        this.details.add(detail);
    }

    public void setStatusList(List<OrderStatus> statusList) {
        statusList.forEach(status -> status.setOrder(this));
        statusList.stream()
                .sorted(Comparator.comparing(OrderStatus::getDateTime).reversed())
                .collect(Collectors.toList());
        this.statusList = statusList;
    }

    public void addStatus(OrderStatus status){
        if (this.statusList==null){
            this.statusList = new ArrayList<>();
        }
        status.setOrder(this);
        this.statusList.add(status);
        this.statusList.stream()
                .sorted(Comparator.comparing(OrderStatus::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    public void setUserOrder(UserOrder userOrder) {
        userOrder.setOrder(this);
        this.userOrder = userOrder;
    }

    @Override
    public String toString() {
        return "Order{" +
                "details=" + details +
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
                Objects.equals(creationDateTime, order.creationDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), totalPrice, creationDateTime);
    }
}
