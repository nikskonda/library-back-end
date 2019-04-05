package by.bntu.fitr.service.user;

import by.bntu.firt.NotFoundException;
import by.bntu.firt.model.user.User;
import by.bntu.fitr.converter.UserDtoConverter;
import by.bntu.fitr.dto.UserDto;
import by.bntu.repository.user.RoleRepository;
import by.bntu.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private static final String SERVICE_ERROR = "exception.service_error.%s.%s";
    private static final String NOT_FOUND_ERROR = "exception.not_found.user";

    private static final String DEFAULT_ROLE = "USER";

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserDtoConverter userDtoConverter;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       UserDtoConverter userDtoConverter,
                       BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userDtoConverter = userDtoConverter;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserDto save(UserDto userDto){
        User user = this.userDtoConverter.convertFromDto(userDto);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return userDtoConverter.convertToDto(userRepository.save(user));
//                .orElseThrow(() -> new ServiceException(String.format(SERVICE_ERROR, "creation", "user"))));
     }

     public UserDto find(Long id){
         return userDtoConverter.convertToDto(userRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
     }


    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public void delete(UserDto userDto){
        userRepository.delete(userDtoConverter.convertFromDto(userDto));
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
