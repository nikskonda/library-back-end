package by.bntu.fitr.model.book;

import by.bntu.fitr.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "Language")
@Table(name = "language", schema = "public")
@SequenceGenerator(name = "id_generator", sequenceName = "language_sequence", allocationSize = 1)
@AttributeOverride(name = "id", column = @Column(name = "language_id"))
@DynamicUpdate
public class Language extends BaseEntity {

    @Column(name = "language_name", nullable = false, length = 20, unique = true)
    private String name;

    @Column(name = "language_tag", nullable = false, length = 5, unique = true)
    private String tag;

}
