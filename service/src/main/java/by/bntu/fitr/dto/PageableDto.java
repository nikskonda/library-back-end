package by.bntu.fitr.dto;

import lombok.Data;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Min;

@Data
public class PageableDto {

    @Min(value = 0, message="exception.validation.pageable.number.min")
    private Integer number;
    @Min(value = 1, message="exception.validation.pageable.number.min")
    private Integer size;
    private String[] sort;
    private Sort.Direction direction;

    public PageableDto() {
        this.number = 0;
        this.size = 20;
        this.sort = new String[]{"id"};
        this.direction = Sort.Direction.ASC;
    }

    public void setNumber(Integer number) {
        if (number>0){
            this.number = number-1;
        }
    }
}
