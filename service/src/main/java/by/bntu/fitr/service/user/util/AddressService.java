package by.bntu.fitr.service.user.util;

import by.bntu.fitr.AccessDeniedException;
import by.bntu.fitr.NotFoundException;
import by.bntu.fitr.converter.AbstractDtoConverter;
import by.bntu.fitr.dto.user.util.AddressDto;
import by.bntu.fitr.model.user.User;
import by.bntu.fitr.model.user.util.Address;
import by.bntu.fitr.repository.user.util.AddressRepository;
import by.bntu.fitr.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AddressService {

    private static final String NOT_FOUND_ERROR = "exception.not_found.address";
    private static final String ROLE_FOR_ADDRESS_EDIT = "ADMIN";

    private AddressRepository repository;
    private AbstractDtoConverter<Address, AddressDto> converter;
    private UserService userService;

    @Autowired
    public AddressService(AddressRepository repository, AbstractDtoConverter<Address, AddressDto> converter, UserService userService) {
        this.repository = repository;
        this.converter = converter;
        this.userService = userService;
    }

    public AddressDto save(AddressDto addressDto, String username){
        addressDto.setUser(userService.find(username));
        addressDto.setCreationDateTime(LocalDateTime.now());
        return converter.convertToDto(repository.save(converter.convertFromDto(addressDto)));
    }

    public AddressDto find(Long id, String username){
        Address address = getPersistence(id);
        checkAccess(username, address.getUser());
        return converter.convertToDto(address);
    }

    public AddressDto findMain(String username){
        Address address = findMainAddress(username);
        if (address!=null){
            return converter.convertToDto(address);
        }
        return null;
    }

    private Address findMainAddress(String username){
        return repository.findAddressByUserUsernameAndMainTrue(username);
    }

    public void delete(Long id, String username) {
        Address address = getPersistence(id);
        checkAccess(username, address.getUser());
        repository.deleteById(id);
    }

    public void delete(AddressDto addressDto, String username){
        delete(addressDto.getId(), username);
    }

    public List<AddressDto> findByUsername(String username){
        return converter.convertToDtoList(repository.findAddressesByUserUsernameOrderByCreationDateTimeDesc(username));
    }

    public Address getPersistence(Long id){
        System.out.println("id="+id);
        return repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR));
    }

    public void removeMainAddress(String username){
        Address address = findMainAddress(username);
        if (address!=null){
            address.setMain(false);
            save(converter.convertToDto(address), username);
        }
    }

    private boolean isOwnerAccess(String username, User addressUser) {
        return username.equals(addressUser.getUsername());
    }

    private boolean isAdminAccess(String username) {
        return userService
                .getPersistence(username)
                .getAuthorities()
                .contains(userService.findRole(ROLE_FOR_ADDRESS_EDIT));
    }


    private boolean checkAccess(String username, User addressUser) {
        if (isOwnerAccess(username, addressUser) || isAdminAccess(username)) {
            return true;
        } else {
            throw new AccessDeniedException();
        }
    }
}
