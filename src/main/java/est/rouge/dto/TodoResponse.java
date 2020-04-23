package est.rouge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Class represents for response of todo api
 * 
 * @author tailocphanhuu
 */
@Data
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
