package by.bntu.fitr.service.user;

import by.bntu.fitr.NotFoundException;
import by.bntu.fitr.UnsupportedOperationException;
import by.bntu.fitr.converter.user.UserDataDtoConverter;
import by.bntu.fitr.dto.PageableDto;
import by.bntu.fitr.dto.user.UserDataDto;
import by.bntu.fitr.model.user.UserData;
import by.bntu.fitr.repository.user.UserDataRepository;
import by.bntu.fitr.service.user.util.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class UserDataService {

    private static final String SERVICE_ERROR = "exception.service_error.%s.%s";
    private static final String NOT_FOUND_ERROR = "exception.not_found.user";

    private UserDataRepository userRepository;
    private UserService userService;
    private AddressService addressService;
    private UserDataDtoConverter userDtoConverter;

    @Autowired
    public UserDataService(UserDataRepository userRepository, UserService userService, AddressService addressService, UserDataDtoConverter userDtoConverter) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.addressService = addressService;
        this.userDtoConverter = userDtoConverter;
    }

    public UserDataDto save(UserDataDto userDataDto, String username) {
        UserData user = this.userDtoConverter.convertFromDto(userDataDto);
        user.setUsername(username);
        if (user.getId() != null && userRepository.existsById(user.getId()) &&
                !StringUtils.isEmpty(user.getUsername()) && userRepository.existsByUsername(user.getUsername())) {
            if (userDataDto.getRegistrationAddress()!=null){
                if (userDataDto.getRegistrationAddress().getId() == null) {
                    userDataDto.setRegistrationAddress(addressService.save(userDataDto.getRegistrationAddress()));
                }
                user.setRegistrationAddress(addressService.getPersistence(userDataDto.getRegistrationAddress().getId()));
            }
            user = userRepository.save(user);
            return userDtoConverter.convertToDto(user);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public UserDataDto find(Long id) {
        UserDataDto user = userDtoConverter.convertToDto(getPersistence(id));
        return user;
    }


    public UserDataDto find(String username) {
        UserDataDto user = userDtoConverter.convertToDto(getPersistence(username));
        return user;
    }


    public void clear(Long id) {
        UserData user = getPersistence(id);
        save(new UserDataDto(id, user.getUsername()), user.getUsername());
    }

    public void clear(String username) {
        save(new UserDataDto(find(username).getId(), username), username);
    }

    private UserData getPersistence(Long id) {
        System.out.println("UserDataService id=" + id);
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR));
    }

    private UserData getPersistence(String username) {
        System.out.println("UserDataService username=" + username);
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR));
    }


    public Page<UserDataDto> findAllByUsername(String searchString, PageableDto pageableDto) {
        if (searchString == null) {
            searchString = "";
        }
        Pageable pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize(), pageableDto.getDirection(), pageableDto.getSort());
        Page<UserDataDto> page = userDtoConverter.convertToDtoPage(userRepository.findUserDataByUsernameLike('%' + searchString + '%', pageable));
        for (UserDataDto userDataDto : page.getContent()){
            userDataDto.setBanned(userService.isBanned(userDataDto.getUsername()));
        }
        return page;
    }

}


