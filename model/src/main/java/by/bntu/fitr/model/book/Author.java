package by.bntu.fitr.model.book;

import by.bntu.fitr.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;


@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "Author")
@Table(name = "author", schema = "public")
@SequenceGenerator(name = "id_generator", sequenceName = "author_sequence", allocationSize = 1)
@AttributeOverride(name = "id", column = @Column(name = "author_id"))
@DynamicUpdate
public class Author extends BaseEntity {

    @Column(name = "author_first_name", nullable = false, length = 30)
    private String firstName;
    @Column(name = "author_last_name", nullable = false, length = 30)
    private String lastName;

    @Column(name = "author_description")
    private String description;
    @Column(name = "author_wiki_link")
    private String wikiLink;

}
