package by.bntu.fitr.service.user;

import by.bntu.fitr.NotFoundException;
import by.bntu.fitr.converter.user.UserDtoConverter;
import by.bntu.fitr.dto.user.UserDto;
import by.bntu.fitr.model.user.Role;
import by.bntu.fitr.model.user.User;
import by.bntu.fitr.repository.user.RoleRepository;
import by.bntu.fitr.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserService {

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
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userDtoConverter = userDtoConverter;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserDto save(UserDto userDto) {
        User user = this.userDtoConverter.convertFromDto(userDto);
        if (user.getAuthorities() == null || user.getAuthorities().isEmpty()) {
            user.setAuthorities(new HashSet<>());
        } else {
            Set<Role> roles = new HashSet<>(user.getAuthorities());
            user.setAuthorities(new HashSet<>());
            for (Role role : roles) {
                if (roleRepository.existsByAuthority(role.getAuthority())) {
                    user.getAuthorities().add(findRole(role.getAuthority()));
                } else {
                    user.getAuthorities().add(roleRepository.save(role));
                }
            }
        }

        setDefaultRole(user);
        if (user.getPassword() != null) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        } else if (userRepository.existsByUsername(user.getUsername())) {
            user.setPassword(find(user.getUsername()).getPassword());
        }
        user.setRegistrationDate(LocalDateTime.now());
        user = userRepository.save(user);
        return userDtoConverter.convertToDto(user);
//                .orElseThrow(() -> new ServiceException(String.format(SERVICE_ERROR, "creation", "user"))));
    }

    public UserDto find(Long id) {
        return userDtoConverter.convertToDto(getPersistence(id));
    }

    public UserDto find(String username) {
        return userDtoConverter.convertToDto(getPersistence(username));
    }


    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public void delete(UserDto userDto) {
        userRepository.delete(userDtoConverter.convertFromDto(userDto));
    }

    public Role findRole(String authority) {
        return roleRepository.findByAuthority(authority).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR));
    }

    public User getPersistence(Long id) {
        return (userRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
    }

    public User getPersistence(String username) {
        return (userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
    }

    public Boolean isBanned(String username){
        return userRepository.isBaned(username);
    }

    private void setDefaultRole(User user) {
        Role defaultUserRole;
        try {
            defaultUserRole = findRole(DEFAULT_ROLE);

        } catch (NotFoundException ex) {
            defaultUserRole = roleRepository.save(new Role(DEFAULT_ROLE));
        }
        if (!user.getAuthorities().contains(defaultUserRole)) {
            user.getAuthorities().add(defaultUserRole);
        }
    }

}
