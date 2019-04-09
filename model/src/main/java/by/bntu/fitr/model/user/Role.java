package by.bntu.fitr.model.user;

import by.bntu.fitr.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "Role")
@Table(name = "role", schema = "public")
@SequenceGenerator(name = "id_generator", sequenceName = "role_sequence", allocationSize = 1)
@AttributeOverride(name = "id", column = @Column(name = "role_id"))
public class Role extends BaseEntity implements GrantedAuthority {

    @Column(name = "role_authority", unique = true, nullable = false)
    private String authority;

    public Role(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
