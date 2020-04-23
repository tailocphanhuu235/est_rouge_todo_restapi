package est.rouge.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class define error common
 * 
 * @author tailocphanhuu
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommonError {
    /**
     * Error code
     */
    private String code;

    /**
     * Error message
     */
    private String message;

    /**
     * Error item
     */
    private String item;
}
