package by.bntu.fitr.dto.news;

import by.bntu.fitr.dto.book.LanguageDto;
import by.bntu.fitr.dto.user.UserDataDto;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class NewsDto {

    @Null(message = "exception.validation.news.id.null")
    private Long id;
    @NotNull(message = "exception.validation.news.language.notNull")
    private LanguageDto language;
    @NotNull(message = "exception.validation.news.title.notNull")
    @Size(min = 1, max = 255, message = "exception.validation.news.title.size")
    private String title;
    @NotNull(message = "exception.validation.news.pictureUrl.notNull")
    @Size(min = 1, max = 255, message = "exception.validation.news.pictureUrl.size")
    private String pictureUrl;
    @NotNull(message = "exception.validation.news.thumbnailUrl.notNull")
    @Size(min = 1, max = 255, message = "exception.validation.news.thumbnailUrl.size")
    private String thumbnailUrl;
    @NotNull(message = "exception.validation.news.text.notNull")
    @Size(min = 1, max = 10000, message = "exception.validation.news.text.size")
    private String text;

    private UserDataDto creator;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;

}
