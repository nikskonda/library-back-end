package by.bntu.fitr.service.user;

import by.bntu.fitr.NotFoundException;
import by.bntu.fitr.UnsupportedOperationException;
import by.bntu.fitr.converter.user.UserMainDataDtoConverter;
import by.bntu.fitr.dto.PageableDto;
import by.bntu.fitr.dto.user.UserMainDataDto;
import by.bntu.fitr.model.user.Role;
import by.bntu.fitr.model.user.UserMainData;
import by.bntu.fitr.repository.user.RoleRepository;
import by.bntu.fitr.repository.user.UserMainDataRepository;
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
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserMainDataService implements UserDetailsService {

    private static final String SERVICE_ERROR = "exception.service_error.%s.%s";
    private static final String NOT_FOUND_ERROR = "exception.not_found.user";

    private static final String DEFAULT_ROLE = "USER";

    private UserMainDataRepository userRepository;
    private RoleRepository roleRepository;
    private UserMainDataDtoConverter userDtoConverter;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserMainDataService(UserMainDataRepository userRepository,
                               RoleRepository roleRepository,
                               UserMainDataDtoConverter userDtoConverter,
                               BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userDtoConverter = userDtoConverter;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserMainDataDto save(UserMainDataDto userMainDataDto){
        UserMainData user = this.userDtoConverter.convertFromDto(userMainDataDto);

        if (user.getId()!=null && userRepository.existsById(user.getId()) &&
                !StringUtils.isEmpty(user.getUsername()) && userRepository.existsByUsername(user.getUsername())){
            if (user.getAuthorities()==null || user.getAuthorities().isEmpty()){
                user.setAuthorities(new HashSet<>());
            } else {
                Set<Role> roles = new HashSet<>(user.getAuthorities());
                user.setAuthorities(new HashSet<>());
                for (Role role: roles){
                    if (roleRepository.existsByAuthority(role.getAuthority())){
                        user.getAuthorities().add(findRole(role.getAuthority()));
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

        } else {
            throw new UnsupportedOperationException();
        }
//                .orElseThrow(() -> new ServiceException(String.format(SERVICE_ERROR, "creation", "user"))));
     }

     public UserMainDataDto find(Long id){
         return userDtoConverter.convertToDto(userRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
     }

    public UserMainDataDto find(String username){
        return userDtoConverter.convertToDto(userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
    }

    public Role findRole(String authority){
       return roleRepository.findByAuthority(authority).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR));
    }

    public void addRole(String username, String authority){
        UserMainData user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR));
        Role role = findRole(authority);
        if (!user.getAuthorities().contains(role)){
            user.getAuthorities().add(role);
            userRepository.save(user);
        }
    }

    public void addRole(Long userId, String authority){
        UserMainData user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR));
        Role role = findRole(authority);
        if (!user.getAuthorities().contains(role)){
            user.getAuthorities().add(role);
            userRepository.save(user);
        }
    }

    public void deleteRole(String username, String authority){
        UserMainData user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR));
        Role role = findRole(authority);
        if (!user.getAuthorities().contains(role)){
            user.getAuthorities().remove(role);
            userRepository.save(user);
        }
    }

    public void deleteRole(Long userId, String authority){
        UserMainData user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR));
        Role role = findRole(authority);
        if (!user.getAuthorities().contains(role)){
            user.getAuthorities().remove(role);
            userRepository.save(user);
        }
    }

    public void ban(Long id) {
        UserMainData user = getPersistence(id);
        changeBan(user);
        userRepository.save(user);
    }

    public void ban(String username) {
        UserMainData user = getPersistence(username);
        changeBan(user);
        userRepository.save(user);
    }

    private void changeBan(UserMainData user){
        user.setEnabled(!user.getEnabled());
    }

    public UserMainData getPersistence(Long id) {
        return (userRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
    }

    public UserMainData getPersistence(String username) {
        Optional<UserMainData> user = userRepository.findByUsername(username);
        return (userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
    }

    public Page<UserMainDataDto> findAll(PageableDto pageableDto){
        Pageable pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize(), pageableDto.getDirection(), pageableDto.getSort());
        return userDtoConverter.convertToDtoPage(userRepository.findAll(pageable));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getPersistence(username);
    }

    private void setDefaultRole(UserMainData user) {
        Role defaultUserRole;
        try{
            defaultUserRole = findRole(DEFAULT_ROLE);

        } catch (NotFoundException ex){
            defaultUserRole = roleRepository.save(new Role(DEFAULT_ROLE));
        }
        if (!user.getAuthorities().contains(defaultUserRole)){
            user.getAuthorities().add(defaultUserRole);
        }
    }

}
