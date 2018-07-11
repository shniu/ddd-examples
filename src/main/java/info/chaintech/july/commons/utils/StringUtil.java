package info.chaintech.july.commons.utils;

import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Objects;

/**
 * @author shniu
 * @date 2018-07-11 下午8:12
 * @email niushaohan@digcredit.com
 */

public class StringUtil {

    public static final String EMPTY_STRING = "";
    public static final String DELIMITE_SLASH = "/";

    public static String arrayToCommaDelimitedString(Object[] arr) {
        Objects.requireNonNull(arr);
        return StringUtils.arrayToCommaDelimitedString(arr);
    }

    public static String collectionToDelimitedString(Collection<?> coll, String delim) {
        Objects.requireNonNull(coll);
        if (StringUtils.isEmpty(delim)) {
            delim = EMPTY_STRING;
        }
        return StringUtils.collectionToDelimitedString(coll, delim);
    }

}
