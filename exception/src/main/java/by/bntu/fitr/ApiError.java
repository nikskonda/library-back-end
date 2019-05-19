package by.bntu.fitr;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
public class ApiError implements Serializable {

    private String message;

    public ApiError() {
    }

    public ApiError(String message) {
        this.message = message;
    }
}
