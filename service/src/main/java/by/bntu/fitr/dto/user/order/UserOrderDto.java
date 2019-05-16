package by.bntu.fitr.dto.user.order;

import by.bntu.fitr.dto.user.UserDataDto;
import by.bntu.fitr.dto.user.util.AddressDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserOrderDto {

    private AddressDto address;

    private UserDataDto user;

    private LocalDateTime lastModification;

}
