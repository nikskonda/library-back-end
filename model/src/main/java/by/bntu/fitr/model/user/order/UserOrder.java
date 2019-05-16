package by.bntu.fitr.model.user.order;

import by.bntu.fitr.model.BaseEntity;
import by.bntu.fitr.model.user.User;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "UserOrder")
@Table(name = "user_order", schema = "public")
@SequenceGenerator(name = "id_generator", sequenceName = "user_order_sequence", allocationSize = 1)
@AttributeOverride(name = "id", column = @Column(name = "user_order_id"))
@DynamicUpdate
public class UserOrder extends BaseEntity {

    @ManyToOne(cascade = {CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne(cascade = {CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = {CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "LOCAL_STORAGE_BASKET")
    private LocalDateTime lastModification;

    public UserOrder() {
        this.lastModification = LocalDateTime.now();
    }
}
