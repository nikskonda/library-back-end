package by.bntu.fitr.model.user;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "UserData")
@Table(name = "user", schema = "public")
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
@DynamicUpdate
public class UserData extends BaseEntity {

    @Column(name = "user_username", nullable = false, length = 30, unique = true)
    private String username;

    @ManyToMany(cascade = {CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinTable(name = "user_has_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> authorities;

    @Column(name = "user_avatar_url")
    private String avatarUrl;

    @Column(name = "user_first_name", length = 30)
    private String firstName;
    @Column(name = "user_last_name", length = 30)
    private String lastName;

    @Column(name = "user_email", length = 254)
    private String email;

    @ManyToOne(cascade = {CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = "registration_address_id")
    private Address registrationAddress;

    @Column(name = "user_registration_date")
    private LocalDateTime registrationDate;

}
