package by.bntu.fitr.service.user;

import by.bntu.fitr.NotFoundException;
import by.bntu.fitr.UnsupportedOperationException;
import by.bntu.fitr.converter.user.UserDataDtoConverter;
import by.bntu.fitr.dto.PageableDto;
import by.bntu.fitr.dto.user.UserDataDto;
import by.bntu.fitr.model.user.UserData;
import by.bntu.fitr.repository.user.UserDataRepository;
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
    private UserDataDtoConverter userDtoConverter;

    @Autowired
    public UserDataService(UserDataRepository userRepository, UserDataDtoConverter userDtoConverter) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
    }

    public UserDataDto save(UserDataDto userDataDto) {
        UserData user = this.userDtoConverter.convertFromDto(userDataDto);
        if (user.getId() != null && userRepository.existsById(user.getId()) &&
                !StringUtils.isEmpty(user.getUsername()) && userRepository.existsByUsername(user.getUsername())) {
            user = userRepository.save(user);
            return userDtoConverter.convertToDto(user);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public UserDataDto find(Long id) {
        return userDtoConverter.convertToDto(getPersisten(id));
    }

    public UserDataDto find(String username) {
        return userDtoConverter.convertToDto(getPersisten(username));
    }

    public void clear(Long id) {
        save(new UserDataDto(id, find(id).getUsername()));
    }

    public void clear(String username) {
        save(new UserDataDto(find(username).getId(), username));
    }

    private UserData getPersisten(Long id){
        System.out.println("UserDataService id="+id);
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR));
    }

    private UserData getPersisten(String username){
        System.out.println("UserDataService username="+username);
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR));
    }


    public Page<UserDataDto> findAll(PageableDto pageableDto) {
        Pageable pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize(), pageableDto.getDirection(), pageableDto.getSort());
        return userDtoConverter.convertToDtoPage(userRepository.findAll(pageable));
    }
}
