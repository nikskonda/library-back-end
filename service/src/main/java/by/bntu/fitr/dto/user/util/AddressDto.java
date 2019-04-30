package by.bntu.fitr.dto.user.util;

import by.bntu.fitr.dto.user.UserDto;
import by.bntu.fitr.model.BaseEntity;
import by.bntu.fitr.model.user.User;
import by.bntu.fitr.model.user.util.City;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Data
public class AddressDto {

    private Long id;

    private UserDto user;

    private String firstName;
    private String lastName;

    private String phone;

    private String email;

    private CityDto city;

    private String address;

    private Integer postalCode;

    private LocalDateTime creationDateTime;
}
