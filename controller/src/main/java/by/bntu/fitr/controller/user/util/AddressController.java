package by.bntu.fitr.controller.user.util;

import by.bntu.fitr.dto.user.util.AddressDto;
import by.bntu.fitr.service.user.util.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@CrossOrigin
@RequestMapping(value = "/address")
public class AddressController {

    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public AddressDto find(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id,
                           Authentication authentication) {
        return addressService.find(id, authentication.getName());
    }

//    @PostMapping
//    @PreAuthorize("hasAuthority('LIBRARIAN')")
//    @ResponseStatus(HttpStatus.CREATED)
//    public AddressDto create(@Valid @RequestBody AddressDto addressDto,
//                             Authentication authentication) {
//        return addressService.save(addressDto, authentication.getName());
//    }
//
//    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('LIBRARIAN')")
//    public AddressDto update(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id,
//                          @Validated() @RequestBody AddressDto addressDto,
//                             Authentication authentication) {
//        addressDto.setId(id);
//        return addressService.save(addressDto, authentication.getName());
//    }
//
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PreAuthorize("hasAuthority('LIBRARIAN')")
//    public void remove(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long id,
//                       Authentication authentication) {
//        addressService.delete(id, authentication.getName());
//    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<AddressDto> findByUserId(@PathVariable @Min(value = 1, message = "exception.validation.min.id") Long userId) {
        return addressService.findByUserId(userId);
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('USER')")
    public List<AddressDto> findByUsername(Authentication authentication) {
        return addressService.findByUsername(authentication.getName());
    }

}
