package by.bntu.fitr.dto.user;

import by.bntu.fitr.model.BaseEntity;
import by.bntu.fitr.model.user.Role;
import by.bntu.fitr.model.user.util.City;
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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Set;


@Data
public class UserDto {

    private Long id;
    private String username;

    private String password;

    private Set<Role> authorities;

    private Boolean accountNonExpired;

    private Boolean accountNonLocked;

    private Boolean credentialsNonExpired;

    private Boolean enabled;

    private String firstName;
    private String lastName;

    private String email;

    private City city;

    private String address;
    private Integer postalCode;
}
