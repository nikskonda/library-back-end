package by.bntu.fitr.model.user.util;

import by.bntu.fitr.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "Country")
@Table(name = "country", schema = "public")
@SequenceGenerator(name = "id_generator", sequenceName = "country_sequence", allocationSize = 1)
@AttributeOverride(name = "id", column = @Column(name = "country_id"))
public class Country extends BaseEntity {

    @Column(name = "country_name", unique = true, nullable = false)
    private String name;

}
