package by.bntu.fitr.service.user;

import by.bntu.fitr.NotFoundException;
import by.bntu.fitr.UnsupportedOperationException;
import by.bntu.fitr.config.UserRole;
import by.bntu.fitr.converter.user.RoleDtoConverter;
import by.bntu.fitr.converter.user.UserMainDataDtoConverter;
import by.bntu.fitr.dto.PageableDto;
import by.bntu.fitr.dto.user.RoleDto;
import by.bntu.fitr.dto.user.UserMainDataDto;
import by.bntu.fitr.model.user.Role;
import by.bntu.fitr.model.user.UserMainData;
import by.bntu.fitr.repository.user.RoleRepository;
import by.bntu.fitr.repository.user.UserMainDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserMainDataService implements UserDetailsService {

    private static final String SERVICE_ERROR = "exception.service_error.%s.%s";
    private static final String NOT_FOUND_ERROR = "exception.not_found.user";

    private static final Sort.Direction ROLE_SORTING_DIRECTION = Sort.Direction.ASC;
    private static final String[] ROLE_SORTING_FIELDS = {"priority", "authority"};

    private UserRole userRole;

    private UserMainDataRepository userRepository;
    private RoleRepository roleRepository;
    private UserMainDataDtoConverter converter;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RoleDtoConverter roleConverter;

    @Autowired
    public UserMainDataService(UserRole userRole, UserMainDataRepository userRepository, RoleRepository roleRepository, UserMainDataDtoConverter converter, BCryptPasswordEncoder bCryptPasswordEncoder, RoleDtoConverter roleConverter) {
        this.userRole = userRole;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.converter = converter;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleConverter = roleConverter;
    }

    public UserMainDataDto save(UserMainDataDto userMainDataDto) {
        UserMainData user = this.converter.convertFromDto(userMainDataDto);

        if (user.getId() != null && userRepository.existsById(user.getId()) &&
                !StringUtils.isEmpty(user.getUsername()) && userRepository.existsByUsername(user.getUsername())) {
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
                user.setPassword(loadUserByUsername(user.getUsername()).getPassword());
            }

            user = userRepository.save(user);
            return converter.convertToDto(user);

        } else {
            throw new UnsupportedOperationException();
        }
//                .orElseThrow(() -> new ServiceException(String.format(SERVICE_ERROR, "creation", "user"))));
    }

    public UserMainDataDto find(Long id) {
        return converter.convertToDto(userRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
    }

    public UserMainDataDto find(String username) {
        return converter.convertToDto(userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
    }

    public Role findRole(String authority) {
        return roleRepository.findByAuthority(authority).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR));
    }

    public List<RoleDto> findAllRoles(){
        return roleConverter.convertToDtoList(roleRepository.findAll(new Sort(ROLE_SORTING_DIRECTION, ROLE_SORTING_FIELDS)));
    }

    public Role findRoleOrCreate(String authority) {
//        Optional<Role> role = roleRepository.findByAuthority(authority);
        return roleRepository
                .findByAuthority(authority)
                .orElseGet(() -> roleRepository.save(new Role(authority)));
    }

    public UserMainDataDto addRoleByUsername(String username, String authority) {
        UserMainData user = getPersistence(username);
        return addRole(user, authority);
    }

    public UserMainDataDto addRoleByUserId(Long userId, String authority) {
        UserMainData user = getPersistence(userId);
        return addRole(user, authority);
    }

    private UserMainDataDto addRole(UserMainData user, String authority) {
        Role role = findRoleOrCreate(authority);
        if (!user.getAuthorities().contains(role)) {
            user.getAuthorities().add(role);
            user = userRepository.save(user);
        }
        return converter.convertToDto(user);
    }

    public void deleteRoleByUsername(String username, String authority) {
        UserMainData user = getPersistence(username);
        deleteRole(user, authority);
    }

    public void deleteRoleByUserId(Long userId, String authority) {
        UserMainData user = getPersistence(userId);
        deleteRole(user, authority);
    }

    public void deleteRole(UserMainData user, String authority) {
        Role role = findRole(authority);
        if (user.getAuthorities().contains(role)) {
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

    private void changeBan(UserMainData user) {
        user.setEnabled(!user.getEnabled());
    }

    public UserMainData getPersistence(Long id) {
        return (userRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
    }

    public UserMainData getPersistence(String username) {
        Optional<UserMainData> user = userRepository.findByUsername(username);
        return (userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR)));
    }

    public Page<UserMainDataDto> findAll(PageableDto pageableDto) {
        Pageable pageable = PageRequest.of(pageableDto.getNumber(), pageableDto.getSize(), pageableDto.getDirection(), pageableDto.getSort());
        return converter.convertToDtoPage(userRepository.findAll(pageable));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getPersistence(username);
    }

    private void setDefaultRole(UserMainData user) {
        Role defaultUserRole;
        try {
            defaultUserRole = findRole(userRole.getUser());

        } catch (NotFoundException ex) {
            defaultUserRole = roleRepository.save(new Role(userRole.getUser()));
        }
        if (!user.getAuthorities().contains(defaultUserRole)) {
            user.getAuthorities().add(defaultUserRole);
        }
    }

}
