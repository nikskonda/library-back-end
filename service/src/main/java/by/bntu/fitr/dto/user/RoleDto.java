package by.bntu.fitr.dto.user;

import lombok.Data;

@Data
public class RoleDto {

    private Long id;
    private String authority;
    private Integer priority;

    public RoleDto() {
        this.priority = 1;
    }

    public RoleDto(String authority, Integer priority) {
        this.authority = authority;
        this.priority = priority;
    }
}