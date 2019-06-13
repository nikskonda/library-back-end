package by.bntu.fitr.service.user.util;

import by.bntu.fitr.AccessDeniedException;
import by.bntu.fitr.NotFoundException;
import by.bntu.fitr.UnsupportedOperationException;
import by.bntu.fitr.config.UserRole;
import by.bntu.fitr.converter.user.util.AddressDtoConverter;
import by.bntu.fitr.dto.user.util.AddressDto;
import by.bntu.fitr.model.user.Role;
import by.bntu.fitr.model.user.User;
import by.bntu.fitr.model.user.util.Address;
import by.bntu.fitr.repository.user.util.AddressRepository;
import by.bntu.fitr.service.user.UserService;
import by.bntu.fitr.service.user.order.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AddressService {

    private static final String NOT_FOUND_ERROR = "exception.notFound.address";

    private AddressRepository repository;
    private AddressDtoConverter converter;
    private UserService userService;
    private UserOrderService userOrderService;
    private UserRole userRole;

    @Autowired
    public AddressService(AddressRepository repository, AddressDtoConverter converter, UserService userService, UserOrderService userOrderService, UserRole userRole) {
        this.repository = repository;
        this.converter = converter;
        this.userService = userService;
        this.userOrderService = userOrderService;
        this.userRole = userRole;
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

    public AddressDto find(Long id){
        Address address = getPersistence(id);
        return converter.convertToDto(address);
    }

    public List<AddressDto> findByUserId(Long userId){
        String username = userService.getPersistence(userId).getUsername();

        return converter.convertToDtoList(userOrderService.findAddressesByUsername(username));
    }

    public List<AddressDto> findByUsername(String username){
        List<Address> list = userOrderService.findAddressesByUsername(username);
        Address registerAddress = userService.getPersistence(username).getRegistrationAddress();
        if (registerAddress != null){
            list.add(0, registerAddress);
        }
        List<Address> listWithUnique = new ArrayList<>();
        for (Address address : list){
            if (!listWithUnique.contains(address)){
                listWithUnique.add(address);
            }
        }
        return converter.convertToDtoList(listWithUnique);
    }

    public Address getPersistence(Long id){
        System.out.println("id="+id);
        return repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR));
    }

    private boolean isOwnerAccess(String username, User addressUser) {
        return username.equals(addressUser.getUsername());
    }

    private boolean isAdminAccess(String username) {
        Collection<Role> roles = userService.getPersistence(username).getAuthorities();
        for (Role r : roles){
            if (userRole.getOperator().equals(r.getAuthority())){
                return true;
            }
        }
        return false;
    }

    private boolean checkAccess(String username, User addressUser) {
        if (isOwnerAccess(username, addressUser) || isAdminAccess(username)) {
            return true;
        } else {
            throw new AccessDeniedException();
        }
    }
}
