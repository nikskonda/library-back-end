package by.bntu.fitr.dto.news;

import by.bntu.fitr.model.user.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NewsCoverDto {

    private Long id;
    private String title;
    private String thumbnailUrl;
    private User creator;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;

}
