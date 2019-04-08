package by.bntu.fitr.model.book;

import by.bntu.fitr.model.BaseEntity;
import by.bntu.fitr.model.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "Bookmark")
@Table(name = "bookmark", schema = "public")
@SequenceGenerator(name = "id_generator", sequenceName = "bookmark_sequence", allocationSize = 1)
@AttributeOverride(name = "id", column = @Column(name = "bookmark_id"))
@DynamicUpdate
public class Bookmark extends BaseEntity {

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


}
