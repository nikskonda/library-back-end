package by.bntu.fitr.service.book;

import by.bntu.firt.NotFoundException;
import by.bntu.fitr.converter.book.AuthorDtoConverter;
import by.bntu.fitr.converter.book.BookmarkDtoConverter;
import by.bntu.fitr.dto.PageableDto;
import by.bntu.fitr.dto.book.AuthorDto;
import by.bntu.fitr.dto.book.BookmarkDto;
import by.bntu.fitr.model.book.Bookmark;
import by.bntu.fitr.model.user.User;
import by.bntu.fitr.repository.book.AuthorRepository;
import by.bntu.fitr.repository.book.BookmarkRepository;
import by.bntu.fitr.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BookmarkService {

    private static final String NOT_FOUND_ERROR = "exception.not_found.book";

    private BookmarkRepository repository;
    private BookmarkDtoConverter converter;
    private UserService userService;

    @Autowired
    public BookmarkService(BookmarkRepository repository, BookmarkDtoConverter converter, UserService userService) {
        this.repository = repository;
        this.converter = converter;
        this.userService = userService;
    }

    public BookmarkDto save(BookmarkDto bookmarkDto, String username){
        Bookmark bookmark = converter.convertFromDto(bookmarkDto);
        bookmark.setUser((User)userService.loadUserByUsername(username));
        return converter.convertToDto(repository.save(bookmark));
//                .orElseThrow(() -> new ServiceException(String.format(SERVICE_ERROR, "creation", "user"))));
    }

    public BookmarkDto find(Long id){
        return converter.convertToDto(repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void delete(BookmarkDto bookmarkDto){
        repository.delete(converter.convertFromDto(bookmarkDto));
    }

    public Page<BookmarkDto> findAll(PageableDto pageableDto){
        Pageable pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize(), pageableDto.getDirection(), pageableDto.getSort());
        return  converter.convertToDtoPage(repository.findAll(pageable));
    }
}
