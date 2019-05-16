package by.bntu.fitr.service.user.util;

import by.bntu.fitr.AccessDeniedException;
import by.bntu.fitr.NotFoundException;
import by.bntu.fitr.UnsupportedOperationException;
import by.bntu.fitr.converter.AbstractDtoConverter;
import by.bntu.fitr.dto.user.util.AddressDto;
import by.bntu.fitr.model.user.User;
import by.bntu.fitr.model.user.util.Address;
import by.bntu.fitr.repository.user.util.AddressRepository;
import by.bntu.fitr.service.user.UserMainDataService;
import by.bntu.fitr.service.user.order.UserOrderService;
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
    private UserMainDataService userService;
    private UserOrderService userOrderService;

    @Autowired
    public AddressService(AddressRepository repository, AbstractDtoConverter<Address, AddressDto> converter, UserMainDataService userService, UserOrderService userOrderService) {
        this.repository = repository;
        this.converter = converter;
        this.userService = userService;
        this.userOrderService = userOrderService;
    }


    public AddressDto save(AddressDto addressDto){
        if (addressDto.getId()!=null){
            throw new UnsupportedOperationException();
        }
        addressDto.setCreationDateTime(LocalDateTime.now());
        return converter.convertToDto(repository.save(converter.convertFromDto(addressDto)));
    }

    public void setAddress(User user, AddressDto addressDto){
        if (addressDto!=null){
            if (addressDto.getId() == null) {
                addressDto = this.save(addressDto);
            }
            user.setRegistrationAddress(converter.convertFromDto(addressDto));
        }
    }

    public AddressDto find(Long id, String username){
        Address address = getPersistence(id);
        return converter.convertToDto(address);
    }

    public List<AddressDto> findByUserId(Long userId){
        String username = userService.getPersistence(userId).getUsername();
        return converter.convertToDtoList(userOrderService.findAddressesByUsername(username));
    }

    public List<AddressDto> findByUsername(String username){
        return converter.convertToDtoList(userOrderService.findAddressesByUsername(username));
    }

    public Address getPersistence(Long id){
        System.out.println("id="+id);
        return repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR));
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
