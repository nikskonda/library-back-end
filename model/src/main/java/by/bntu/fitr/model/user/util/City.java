package by.bntu.fitr.model.user.util;

import by.bntu.fitr.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "City")
@Table(name = "city", schema = "public")
@SequenceGenerator(name = "id_generator", sequenceName = "city_sequence", allocationSize = 1)
@AttributeOverride(name = "id", column = @Column(name = "city_id"))
public class City extends BaseEntity {

    @Column(name = "city_name", unique = true, nullable = false)
    private String name;

    @OneToOne(cascade = {CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinColumn(name = "state_id", referencedColumnName = "state_id")
    private State state;

}
