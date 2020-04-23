package est.rouge.common.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class define response common
 * 
 * @author tailocphanhuu
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommonResponse<T> {
    /**
     * Error list
     */
    @JsonInclude(Include.NON_EMPTY)
    @JsonProperty("errors")
    private List<CommonError> errors = new ArrayList<CommonError>();

    /**
     * Result
     */
    @JsonInclude(Include.NON_NULL)
    @JsonProperty("result")
    private T result;
}
