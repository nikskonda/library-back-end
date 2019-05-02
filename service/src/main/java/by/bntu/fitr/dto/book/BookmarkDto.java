package by.bntu.fitr.dto.book;

import by.bntu.fitr.dto.user.UserMainDataDto;
import by.bntu.fitr.model.book.Bookmark;
import by.bntu.fitr.model.user.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookmarkDto {

    private Long id;
    private Integer page;

    private BookDto book;

    private UserMainDataDto user;

    private LocalDateTime dateTime;

    private Bookmark.Type type;
}
