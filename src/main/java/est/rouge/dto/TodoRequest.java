package est.rouge.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonProperty;

import est.rouge.common.constants.Constants;
import est.rouge.validation.annotation.DateTime;
import lombok.Data;

/**
 * Class represents for request of todo api
 * 
 * @author tailocphanhuu
 */
@Data
public class TodoRequest {
    /**
     * Work name
     */
    @NotNull(message = Constants.ERROR_CODE_003)
    @Size(min = 1, max = 50, message = Constants.ERROR_CODE_002)
    @JsonProperty("work_name")
    private String workName;

    /**
     * Start date
     */
    @NotNull(message = Constants.ERROR_CODE_003)
    @DateTime(message = Constants.ERROR_CODE_004)
    @JsonProperty("start_date")
    private String startDate;

    /**
     * End date
     */
    @NotNull(message = Constants.ERROR_CODE_003)
    @DateTime(message = Constants.ERROR_CODE_004)
    @JsonProperty("end_date")
    private String endDate;

    /**
     * Status (0: Planning, 1: Doing, 2: Complete)
     */
    @NotNull(message = Constants.ERROR_CODE_003)
    @Range(min = 0, max = 2, message = Constants.ERROR_CODE_006)
    @JsonProperty("status")
    private Integer status;
}
