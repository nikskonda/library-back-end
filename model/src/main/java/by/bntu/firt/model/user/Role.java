package by.bntu.firt.model.user;

import by.bntu.firt.model.BaseEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity(name = "Role")
@Table(name = "role", schema = "public")
public class Role extends BaseEntity implements GrantedAuthority {

    @Column(name = "role_authority", unique = true, nullable = false)
    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }
}
