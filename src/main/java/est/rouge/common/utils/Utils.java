package est.rouge.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import est.rouge.common.constants.Constants;

/**
 * Class utils
 * 
 * @author tailocphanhuu
 */
public class Utils {
    /**
     * Format date to string format yyyy/MM/dd
     * 
     * @param date
     *            format date
     * @return formatted date string
     */
    public static String formatDate_yyyyMMdd(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_YYYY_MM_DD);
        return sdf.format(date);
    }

    /**
     * Convert date string format yyyy/MM/dd to date
     * 
     * @param date
     *            date string
     * @return date
     */
    public static Date convertDate_yyyyMMdd(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_YYYY_MM_DD);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Check validate date time
     * 
     * @param pattern
     *            pattern format
     * @param value
     *            date string
     * @return boolean
     */
    public static boolean isValidDateTimeFormat(String pattern, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (Exception ex) {
            return false;
        }
        return date != null;
    }
}
