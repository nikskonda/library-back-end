package by.bntu.fitr.service.book;

import by.bntu.firt.NotFoundException;
import by.bntu.fitr.converter.book.AuthorDtoConverter;
import by.bntu.fitr.converter.book.BookDtoConverter;
import by.bntu.fitr.dto.book.AuthorDto;
import by.bntu.fitr.dto.book.BookDto;
import by.bntu.fitr.model.book.Book;
import by.bntu.fitr.model.book.Organization;
import by.bntu.fitr.model.book.PublishingHouse;
import by.bntu.fitr.repository.book.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BookService {

    private static final String NOT_FOUND_ERROR = "exception.not_found.book";

    private BookRepository repository;
    private OrganizationRepository organizationRepository;
    private AuthorRepository authorRepository;
    private PublishingHouseRepository publishingHouseRepository;
    private GenreRepository genreRepository;
    private BookDtoConverter converter;

    @Autowired
    public BookService(BookRepository repository, BookDtoConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public BookDto save(BookDto bookDto){
        Book book = converter.convertFromDto(bookDto);
        if (book.getId()!=null && repository.existsById(book.getId())){
            setProducer(book);
            setImporter(book);
            setPublishingHouse(book);
            setAuthor(book);
            setGenre(book);
        } else {
            return converter.convertToDto(repository.save(book));
        }
//                .orElseThrow(() -> new ServiceException(String.format(SERVICE_ERROR, "creation", "user"))));
    }

    private void setProducer(Book book){
        Organization producer = book.getProducer();
        if (producer == null){
            return;
        }
        if (producer.getId()!=null && organizationRepository.existsById(producer.getId())){
            book.setProducer(organizationRepository
                    .findById(producer.getId())
                    .orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
        } else {
            producer.setId(null);
            book.setProducer(organizationRepository.save(producer));
        }

    }

    private void setPublishingHouse(Book book){
        PublishingHouse importer = book.getPublishingHouse();
        if (importer == null){
            return;
        }
        if (importer.getId()!=null && organizationRepository.existsById(importer.getId())){
            book.setImporter(organizationRepository
                    .findById(importer.getId())
                    .orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
        } else {
            importer.setId(null);
            book.setImporter(organizationRepository.save(importer));
        }
    }

    private void setImporter(Book book){
        Organization importer = book.getImporter();
        if (importer == null){
            return;
        }
        if (importer.getId()!=null && organizationRepository.existsById(importer.getId())){
            book.setImporter(organizationRepository
                    .findById(importer.getId())
                    .orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
        } else {
            importer.setId(null);
            book.setImporter(organizationRepository.save(importer));
        }
    }


    public BookDto find(Long id){
        return converter.convertToDto(repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void delete(BookDto bookDto){
        repository.delete(converter.convertFromDto(bookDto));
    }

}
