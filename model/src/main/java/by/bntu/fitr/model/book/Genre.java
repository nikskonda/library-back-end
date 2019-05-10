package by.bntu.fitr.model.book;

import by.bntu.fitr.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "Genre")
@Table(name = "genre", schema = "public")
@SequenceGenerator(name = "id_generator", sequenceName = "genre_sequence", allocationSize = 1)
@AttributeOverride(name = "id", column = @Column(name = "genre_id"))
@DynamicUpdate
public class Genre extends BaseEntity {

    @Column(name = "genre_name", nullable = false, unique = true)
    private String name;

    public Genre() {
    }

    public Genre(String name) {
        this.name = name;
    }
}
