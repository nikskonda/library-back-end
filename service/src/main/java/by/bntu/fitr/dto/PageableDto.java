package by.bntu.fitr.dto;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class PageableDto {

    private Integer number;
    private Integer size;
    private String[] sort;
    private Sort.Direction direction;

    public PageableDto() {
        this.sort = new String[]{"id"};
        this.direction = Sort.Direction.ASC;
    }
}
