package by.bntu.fitr.service.user;

import by.bntu.firt.NotFoundException;
import by.bntu.fitr.converter.user.UserDtoConverter;
import by.bntu.fitr.dto.PageableDto;
import by.bntu.fitr.dto.user.UserDto;
import by.bntu.fitr.model.user.Role;
import by.bntu.fitr.model.user.User;
import by.bntu.fitr.repository.user.RoleRepository;
import by.bntu.fitr.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

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
        if (user.getAuthorities()==null || user.getAuthorities().isEmpty()){
            user.setAuthorities(new HashSet<>());
        } else {
            Set<Role> roles = new HashSet<>(user.getAuthorities());
            user.setAuthorities(new HashSet<>());
            for (Role role: roles){
                if (roleRepository.existsByAuthority(role.getAuthority())){
                    user.getAuthorities().add(roleRepository.findByAuthority(role.getAuthority()));
                } else {
                    user.getAuthorities().add(roleRepository.save(role));
                }
            }
        }

        setDefaultRole(user);
        if (user.getPassword()!=null){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        } else if (userRepository.existsByUsername(user.getUsername())) {
            user.setPassword(loadUserByUsername(user.getUsername()).getPassword());
        }

        user = userRepository.save(user);
        return userDtoConverter.convertToDto(user);
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

    public void addRole(String username, String authority){
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR));
        Role role = roleRepository.findByAuthority(authority);
        if (!user.getAuthorities().contains(role)){
            user.getAuthorities().add(role);
            userRepository.save(user);
        }
    }

    public void addRole(Long userId, String authority){
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR));
        Role role = roleRepository.findByAuthority(authority);
        if (!user.getAuthorities().contains(role)){
            user.getAuthorities().add(role);
            userRepository.save(user);
        }
    }

    public void deleteRole(String username, String authority){
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR));
        Role role = roleRepository.findByAuthority(authority);
        if (!user.getAuthorities().contains(role)){
            user.getAuthorities().remove(role);
            userRepository.save(user);
        }
    }

    public void deleteRole(Long userId, String authority){
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR));
        Role role = roleRepository.findByAuthority(authority);
        if (!user.getAuthorities().contains(role)){
            user.getAuthorities().remove(role);
            userRepository.save(user);
        }
    }

    public Page<UserDto> findAll(PageableDto pageableDto){
        Pageable pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize(), pageableDto.getDirection(), pageableDto.getSort());
        return userDtoConverter.convertToDtoPage(userRepository.findAll(pageable));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR));
    }

    private void setDefaultRole(User user) {
        Role defaultUserRole = roleRepository.findByAuthority(DEFAULT_ROLE);
        if (!user.getAuthorities().contains(defaultUserRole)){
            user.getAuthorities().add(defaultUserRole);
        }
    }
}
