package by.bntu.fitr.dto.book;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Data
public class PublishingHouseDto {

    @Null(message = "exception.validation.publishingHouse.id.null")
    private Long id;
    @NotNull(message = "exception.validation.publishingHouse.title.notNull")
    @Size(min = 1, max = 40, message = "exception.validation.publishingHouse.title.size")
    private String title;

    @Size(min = 1, max = 600, message = "exception.validation.publishingHouse.description.size")
    private String description;

    @Size(min = 1, max = 255, message = "exception.validation.publishingHouse.siteLink.size")
    private String siteLink;

    @Size(min = 1, max = 255, message = "exception.validation.publishingHouse.logoUrl.size")
    private String logoUrl;

}
