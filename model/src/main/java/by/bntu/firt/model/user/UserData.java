package by.bntu.firt.model.user;

import by.bntu.firt.model.BaseEntity;
import lombok.Data;

@Data
public class UserData extends BaseEntity {

    private String firstName;
    private String lastName;

    private String email;

    private String country;
    private String state;
    private String city;

    private String address;
    private Integer postalCode;

}
