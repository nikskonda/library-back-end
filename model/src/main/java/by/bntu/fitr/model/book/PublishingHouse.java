package by.bntu.fitr.model.book;

import by.bntu.fitr.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "PublishingHouse")
@Table(name = "publishing_house", schema = "public")
@SequenceGenerator(name = "id_generator", sequenceName = "publishing_house_sequence", allocationSize = 1)
@AttributeOverride(name = "id", column = @Column(name = "publishing_house_id"))
public class PublishingHouse extends BaseEntity {

    @Column(name = "publishing_house_title", nullable = false, length=20, unique = true)
    private String title;
    @Column(name = "publishing_house_description")
    private String description;

    @Column(name = "publishing_house_site_link")
    private String siteLink;
    @Column(name = "publishing_house_logo_url")
    private String logoUrl;
}
