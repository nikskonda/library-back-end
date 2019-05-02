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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "Bookmark")
@Table(
        name = "bookmark",
        schema = "public",
        uniqueConstraints={
                @UniqueConstraint(columnNames = {"book_id", "user_id"})
        })
@SequenceGenerator(name = "id_generator",
        sequenceName = "bookmark_sequence",
        allocationSize = 1)
@AttributeOverride(name = "id",
        column = @Column(name = "bookmark_id"))
@DynamicUpdate
public class Bookmark extends BaseEntity
{

    @JoinColumn(name = "bookmark_page", nullable = false)
    private Integer page;


    @ManyToOne(cascade = CascadeType.MERGE , fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;


    @ManyToOne(cascade = CascadeType.MERGE , fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "bookmark_date_time")
    private LocalDateTime dateTime;

    @Column(name = "bookmark_type")
    private Type type;

    public enum Type{
        EPUB, PDF
    }

}
