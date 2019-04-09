package by.bntu.fitr.model.user;

import by.bntu.fitr.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.FetchMode;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.springframework.security.core.GrantedAuthority;
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
import java.util.Collection;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "UserMainData")
@Table(name = "user", schema = "public")
@SequenceGenerator(name = "id_generator", sequenceName = "user_sequence", allocationSize = 1)
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
@Inheritance(strategy = InheritanceType.JOINED)
@DynamicUpdate
public class UserMainData extends BaseEntity implements UserDetails {

    @Column(name = "user_username", nullable = false, length = 20, unique = true)
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


    public UserMainData(){
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    public UserMainData(String username, String password){
        this();
        this.username = username;
        this.password = password;
    }

    public UserMainData(String username, String password, Set<Role> authorities){
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
