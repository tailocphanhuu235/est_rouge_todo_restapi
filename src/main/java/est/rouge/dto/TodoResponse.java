package est.rouge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * Class represents for response of todo api
 * 
 * @author tailocphanhuu
 */
@Data
@JsonInclude(Include.NON_NULL)
public class TodoResponse {
    /**
     * Primary key
     */
    @JsonProperty("id")
    private Long id;

    /**
     * Work name
     */
    @JsonProperty("workName")
    private String workName;

    /**
     * Start date
     */
    @JsonProperty("startDate")
    private String startDate;

    /**
     * End date
     */
    @JsonProperty("endDate")
    private String endDate;

    /**
     * Status (0: Planning, 1: Doing, 2: Complete) 
     */
    @JsonProperty("status")
    private Integer status;
}
