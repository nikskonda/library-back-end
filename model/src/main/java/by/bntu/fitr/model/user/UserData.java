package by.bntu.fitr.model.user;

import by.bntu.fitr.model.BaseEntity;
import by.bntu.fitr.model.user.util.City;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "UserData")
@Table(name = "user", schema = "public")
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
@DynamicUpdate
public class UserData extends BaseEntity {

    @Column(name = "user_username", nullable = false, length = 20, unique = true)
    private String username;

    @Column(name = "user_first_name", length = 30)
    private String firstName;
    @Column(name = "user_last_name", length = 30)
    private String lastName;

    @Column(name = "user_email", length = 30)
    private String email;

    @ManyToOne(cascade = {CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "user_address", length = 30)
    private String address;
    @Column(name = "user_postalCode", length = 6)
    private Integer postalCode;

}
