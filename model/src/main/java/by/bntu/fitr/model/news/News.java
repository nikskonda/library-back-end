package by.bntu.fitr.model.news;

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
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "News")
@Table(name = "news", schema = "public")
@SequenceGenerator(name = "id_generator", sequenceName = "news_sequence", allocationSize = 1)
@AttributeOverride(name = "id", column = @Column(name = "news_id"))
@DynamicUpdate
public class News extends BaseEntity {

    @Column(name = "news_title")
    private String title;
    @Column(name = "news_thumbnail_url")
    private String thumbnailUrl;
    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User creator;
    @Column(name = "news_creation_date")
    private LocalDateTime creationDate;
    @Column(name = "news_modification_date")
    private LocalDateTime modificationDate;

    @Column(name = "news_picture_url")
    private String pictureUrl;
    @Column(name = "news_text")
    private String text;

}
