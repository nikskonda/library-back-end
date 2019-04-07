package by.bntu.fitr.dto.book;

import lombok.Data;

@Data
public class PublishingHouseDto {

    private Long id;

    private String title;
    private String description;


    private String siteLink;
    private String logoUrl;

}
