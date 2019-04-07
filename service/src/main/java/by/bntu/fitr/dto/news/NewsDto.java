package by.bntu.fitr.dto.news;

import by.bntu.fitr.model.user.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NewsDto {

    private Long id;
    private String title;
    private String pictureUrl;
    private String thumbnailUrl;
    private String text;

    private User creator;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;

}
