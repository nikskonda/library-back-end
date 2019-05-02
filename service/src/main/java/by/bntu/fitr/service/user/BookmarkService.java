package by.bntu.fitr.service.user;

import by.bntu.fitr.AccessDeniedException;
import by.bntu.fitr.NotFoundException;
import by.bntu.fitr.converter.book.BookmarkDtoConverter;
import by.bntu.fitr.dto.PageableDto;
import by.bntu.fitr.dto.book.BookmarkDto;
import by.bntu.fitr.model.book.Bookmark;
import by.bntu.fitr.model.user.User;
import by.bntu.fitr.repository.book.BookmarkRepository;
import by.bntu.fitr.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookmarkService {

    private static final String NOT_FOUND_ERROR = "exception.not_found.book";
    private static final String ROLE_FOR_BOOKMARK_EDIT = "ADMIN";

    private BookmarkRepository repository;
    private BookService bookService;
    private BookmarkDtoConverter converter;
    private UserService userService;

    @Autowired
    public BookmarkService(BookService bookService, BookmarkRepository repository, BookmarkDtoConverter converter, UserService userService) {
        this.repository = repository;
        this.converter = converter;
        this.userService = userService;
        this.bookService = bookService;
    }

    public BookmarkDto save(BookmarkDto bookmarkDto, String username) {
        Bookmark bookmark;
        if (bookmarkDto.getId() == null) {
            bookmarkDto.setBook(bookService.find(bookmarkDto.getBook().getId()));
            bookmark = converter.convertFromDto(bookmarkDto);
            bookmark.setUser(userService.getPersistant(username));
        } else {
            bookmark = getPersist(bookmarkDto.getId());
            checkAccess(username, bookmark.getUser());
            bookmark.setPage(bookmarkDto.getPage());
        }
        bookmark.setDateTime(LocalDateTime.now());
        return converter.convertToDto(repository.save(bookmark));
    }

    public BookmarkDto findByUserAndBookId(String username, Long bookId) {
        Bookmark bookmark = repository.findBookmarkByUserUsernameAndBookId(username, bookId);
        if (bookmark != null) {
            checkAccess(username, bookmark.getUser());
            return converter.convertToDto(bookmark);
        }
        return null;

    }

    public BookmarkDto find(Long id, String username) {
        Bookmark bookmark = getPersist(id);
        checkAccess(username, bookmark.getUser());
        return converter.convertToDto(bookmark);
    }

    public void delete(Long id, String username) {
        find(id, username);
        repository.deleteById(id);
    }

    public void delete(BookmarkDto bookmarkDto, String username) {
        find(bookmarkDto.getId(), username);
        repository.delete(converter.convertFromDto(bookmarkDto));
    }

    public Page<BookmarkDto> findAll(String username, PageableDto pageableDto) {
        Pageable pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize(), pageableDto.getDirection(), pageableDto.getSort());
        return converter.convertToDtoPage(repository.findByUser(userService.getPersistant(username), pageable));
    }

    public Bookmark getPersist(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR));
    }

    private boolean checkAccess(String username, User user) {
        if (!username.equals(user.getUsername()) ||
                !user
                        .getAuthorities()
                        .contains(userService.findRole(ROLE_FOR_BOOKMARK_EDIT))) {
            throw new AccessDeniedException();
        }
        return true;
    }

}
