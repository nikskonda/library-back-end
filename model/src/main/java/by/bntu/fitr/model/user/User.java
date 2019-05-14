package by.bntu.fitr.model.user;

import by.bntu.fitr.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;


@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "User")
@Table(name = "user", schema = "public")
@SequenceGenerator(name = "id_generator", sequenceName = "user_sequence", allocationSize = 1)
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
@Inheritance(strategy = InheritanceType.JOINED)
@DynamicUpdate
public class User extends BaseEntity implements UserDetails {

    @Column(name = "user_username", nullable = false, length = 30, unique = true)
    private String username;

    @Column(name = "user_password", nullable = false)
    private String password;

//    @Fetch(FetchMode.JOIN)
    @ManyToMany(cascade = {CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinTable(name = "user_has_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> authorities;

    @Column(name = "user_account_non_expired")
    private Boolean accountNonExpired;

    @Column(name = "user_account_non_locked")
    private Boolean accountNonLocked;

    @Column(name = "user_credentials_non_expired")
    private Boolean credentialsNonExpired;

    @Column(name = "user_enabled")
    private Boolean enabled;

    @Column(name = "user_first_name", length = 30)
    private String firstName;
    @Column(name = "user_last_name", length = 30)
    private String lastName;

    @Column(name = "user_email", length = 254)
    private String email;

    @Column(name = "user_registration_date")
    private LocalDateTime registrationDate;

    @Column(name = "user_avatar_url")
    private String avatarUrl;

    public User(){
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    public User(String username, String password){
        this();
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, Set<Role> authorities){
        this();
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<Role> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}
