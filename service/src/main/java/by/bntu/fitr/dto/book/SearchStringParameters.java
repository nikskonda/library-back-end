package by.bntu.fitr.dto.book;

import lombok.Data;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
public class SearchStringParameters {

    private String searchString;
    private String langTag;
    private String langId;

    public SearchStringParameters() {
        this.searchString = "";
    }

    public void setSearchString(String searchString) {
        if (!StringUtils.isEmpty(searchString)) {
            this.searchString = searchString;
        }
    }
}
