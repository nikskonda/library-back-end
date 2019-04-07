package by.bntu.fitr.dto.book;

import by.bntu.fitr.model.user.User;
import lombok.Data;

@Data
public class BookmarkDto {

    private Long id;
    private Integer page;

    private BookDto book;

    private User user;


}
