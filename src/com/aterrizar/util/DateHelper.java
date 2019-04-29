package com.aterrizar.util;

import com.aterrizar.util.date.pattern.DatePattern;
import com.aterrizar.util.date.pattern.ISO8601;
import com.aterrizar.util.date.pattern.LatinAmerican;
import com.aterrizar.util.date.pattern.NorthAmerican;

import java.util.Date;

public class DateHelper {
    private static final String ISO8601_DATE_REGEXP = "^(?:[1-9][0-9]{3})\\-?(?:(?:0[13578]|1[02])\\-?31|(?:0[469]|11)\\-?30|02\\-?29|(?:0[0-9]|1[012])\\-?(?:0[1-9]|[12][0-9]))$";
    private static final String LATIN_AMERICAN_DATE_REGEXP = "^(?:31\\/?(?:0?[13578]|1[02])|30\\/?(?:0[13-9]|1[12])|29\\/?02|(?:0[1-9]|[12][0-9])\\/?(?:0[1-9]|1[012]))\\/?[1-9][0-9]{3}$";
    private static final String NORTH_AMERICAN_DATE_REGEXP = "^(?:(?:0[13578]|1[02])\\-?31|(?:0[13-9]|1[12])\\-?30|02\\-?29|(?:0[1-9]|1[012])\\-?(?:0[1-9]|[12][0-9]))\\-?[1-9][0-9]{3}$";

    private static final long DAY_IN_MILLISECONDS = 86400000L;

    public static Date parseFromISO8601(String textDate) {
        DatePattern datePattern = new ISO8601();
        return datePattern.parse(textDate);
    }

    public static Date parseFromLatinAmericanDate(String textDate) {
        DatePattern datePattern = new LatinAmerican();
        return datePattern.parse(textDate);
    }

    public static Date parseFromNorthAmericanDate(String textDate) {
        DatePattern datePattern = new NorthAmerican();
        return datePattern.parse(textDate);
    }

    public static Date parseToDate(String textDate) {
        DatePattern datePattern = matchDatePattern(textDate);
        return datePattern.parse(textDate);
    }

    private static DatePattern matchDatePattern(String textDate) {
        DatePattern datePattern = null;

        if (textDate.matches(ISO8601_DATE_REGEXP)) {
            datePattern = new ISO8601();
        } else if (textDate.matches(LATIN_AMERICAN_DATE_REGEXP)) {
            datePattern = new LatinAmerican();
        } else if (textDate.matches(NORTH_AMERICAN_DATE_REGEXP)) {
            datePattern = new NorthAmerican();
        }
        return datePattern;
    }

    public static int calculateDaysDifference(Date dateOne, Date dateTwo) {
        long differenceInMilliseconds = dateOne.getTime() - dateTwo.getTime();
        long daysDifference = differenceInMilliseconds / DAY_IN_MILLISECONDS;
        return Math.abs(Math.round(daysDifference));
    }

    public static boolean isBefore(Date dateOne, Date dateTwo) {
        return (dateOne.getTime() - dateTwo.getTime()) < 0;
    }
}
