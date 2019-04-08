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
@Entity(name = "State")
@Table(name = "state", schema = "public")
@SequenceGenerator(name = "id_generator", sequenceName = "state_sequence", allocationSize = 1)
@AttributeOverride(name = "id", column = @Column(name = "state_id"))
public class State extends BaseEntity {

    @Column(name = "state_name", unique = true, nullable = false)
    private String name;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id", referencedColumnName = "country_id")
    private Country country;

}
