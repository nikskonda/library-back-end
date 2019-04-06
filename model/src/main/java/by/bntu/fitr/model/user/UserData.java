package by.bntu.fitr.model.user;

import by.bntu.fitr.model.user.util.City;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "UserData")
@Table(name = "user_data", schema = "public")
//@AttributeOverride(name = "id", column = @Column(name = "user_id"))
@DynamicUpdate
public class UserData extends User {

    @Column(name = "user_data_first_name", nullable = false, length = 30)
    private String firstName;
    @Column(name = "user_data_last_name", nullable = false, length = 30)
    private String lastName;

    @Column(name = "user_data_email", nullable = false, length = 30)
    private String email;


    @Column(name = "user_data_city", nullable = false, length = 30)
    private City city;

    @Column(name = "user_data_address", nullable = false, length = 30)
    private String address;
    @Column(name = "user_data_postalCode", nullable = false, length = 30)
    private Integer postalCode;

}
