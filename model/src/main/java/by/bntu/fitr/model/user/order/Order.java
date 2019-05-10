package by.bntu.fitr.model.user.order;

import by.bntu.fitr.model.BaseEntity;
import by.bntu.fitr.model.user.util.Address;
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
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "Order")
@Table(name = "order", schema = "public")
@SequenceGenerator(name = "id_generator", sequenceName = "order_sequence", allocationSize = 1)
@AttributeOverride(name = "id", column = @Column(name = "order_id"))
@DynamicUpdate
public class Order extends BaseEntity {

    @Column(name="order_total_price")
    private BigDecimal totalPrice;

    @ManyToOne(cascade = {CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "order_creation_date_time", nullable = false)
    private LocalDateTime creationDateTime;

}
