package by.bntu.fitr.model.book;

import by.bntu.fitr.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "Organization")
@Table(name = "organization", schema = "public")
@SequenceGenerator(name = "id_generator", sequenceName = "organization_sequence", allocationSize = 1)
@AttributeOverride(name = "id", column = @Column(name = "organization_id"))
public class Organization extends BaseEntity {

    @Column(name = "organization_title", nullable = false, unique = true)
    private String title;

}
