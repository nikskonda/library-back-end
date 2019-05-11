package by.bntu.fitr.model.user;

import by.bntu.fitr.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "UserData")
@Table(name = "user", schema = "public")
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
@DynamicUpdate
public class UserData extends BaseEntity {

    @Column(name = "user_username", nullable = false, length = 30, unique = true)
    private String username;

    @Column(name = "user_avatar_url")
    private String avatarUrl;

    @Column(name = "user_first_name", length = 30)
    private String firstName;
    @Column(name = "user_last_name", length = 30)
    private String lastName;

    @Column(name = "user_email", length = 254)
    private String email;

    @Column(name = "user_registration_date")
    private LocalDateTime registrationDate;
}
