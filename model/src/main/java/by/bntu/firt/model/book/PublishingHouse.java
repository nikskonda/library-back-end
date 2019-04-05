package by.bntu.firt.model.book;

import by.bntu.firt.model.BaseEntity;
import lombok.Data;

@Data
public class PublishingHouse extends BaseEntity {

    private String title;
    private String description;


    private String siteLink;
    private String logoUrl;

}
