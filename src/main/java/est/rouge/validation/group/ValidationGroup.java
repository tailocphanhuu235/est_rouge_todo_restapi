package est.rouge.validation.group;

import javax.validation.GroupSequence;

/**
 * Validation group
 * 
 * @author tailocphanhuu
 */
@GroupSequence({GroupHigh.class, GroupMedium.class, GroupLow.class})
public interface ValidationGroup {

}
