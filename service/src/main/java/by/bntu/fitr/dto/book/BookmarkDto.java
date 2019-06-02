package by.bntu.fitr.dto.book;

import by.bntu.fitr.dto.user.UserMainDataDto;
import by.bntu.fitr.model.book.Bookmark;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@Data
public class BookmarkDto {

//    @Null(message = "exception.validation.bookmark.id.null")
    private Long id;

    @NotNull(message = "exception.validation.bookmark.page.notNull")
    @Min(value = 0, message = "exception.validation.bookmark.page.min")
    private Integer page;

    @NotNull(message = "exception.validation.bookmark.book.notNull")
    private BookDto book;

    private UserMainDataDto user;

    private LocalDateTime dateTime;

    @NotNull(message = "exception.validation.bookmark.type.notNull")
    private Bookmark.Type type;
}
