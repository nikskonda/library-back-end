package by.bntu.fitr;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ApiError {

    private String message;

    public ApiError() {
    }

    public ApiError(String message) {
        this.message = message;
    }
}
