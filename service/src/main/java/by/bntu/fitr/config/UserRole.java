package by.bntu.fitr.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "user.role")
@Getter
@Setter
public class UserRole {
    private String user;
    private String courier;
    private String operator;
    private String journalist;
    private String librarian;
    private String admin;
}
