package by.bntu.fitr.dto.news;

import by.bntu.fitr.dto.book.LanguageDto;
import by.bntu.fitr.dto.user.UserDataDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NewsCoverDto {

    private Long id;
    private LanguageDto language;
    private String title;
    private String thumbnailUrl;
    private UserDataDto creator;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;

}
