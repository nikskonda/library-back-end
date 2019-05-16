package by.bntu.fitr.model.user.util;

import by.bntu.fitr.model.BaseEntity;
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
@Entity(name = "Address")
@Table(name = "address", schema = "public")
@SequenceGenerator(name = "id_generator", sequenceName = "address_sequence", allocationSize = 1)
@AttributeOverride(name = "id", column = @Column(name = "address_id"))
@DynamicUpdate
public class Address extends BaseEntity {

    @Column(name = "address_first_name", length = 30)
    private String firstName;

    @Column(name = "address_last_name", length = 30)
    private String lastName;

    @Column(name = "address_phone", length = 20)
    private String phone;

    @Column(name = "address_email", length = 254)
    private String email;

    @ManyToOne(cascade = {CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "address_address", length = 400)
    private String address;

    @Column(name = "address_postalCode", length = 20)
    private String postalCode;

    @Column(name = "address_creation_date_time", nullable = false)
    private LocalDateTime creationDateTime;

}
