package com.aterrizar.util.DateHelper;

import com.sun.istack.internal.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
    private static final String ISO8601_DATE_PATTERN = "yyyy-MM-dd";
    private static final String LATIN_AMERICAN_DATE_PATTERN = "dd/MM/yyyy";
    private static final String NORTH_AMERICAN_DATE_PATTERN = "MM-dd-yyyy";

    private static final String ISO8601_DATE_REGEXP = "^(?:(?:19|2[0-9])?[0-9]{2})\\-(?:(?:0?[13578]|1[02])\\-31|(?:0?[2469]|11)\\-30|0?2\\-29|(?:0?[0-9]|1[012])\\-(?:0?[1-9]|[12][1-9]))$";
    private static final String LATIN_AMERICAN_DATE_REGEXP = "^(?:31\\/(?:0?[13578]|1[02])|30\\/(?:0?[2469]|11)|29\\/0?2|(?:0?[1-9]|[12][1-9])\\/(?:0?[0-9]|1[012]))\\/(?:(?:19|2[0-9])?[0-9]{2})$";
    private static final String NORTH_AMERICAN_DATE_REGEXP = "^(?:(?:0?[13578]|1[02])\\-31|(?:0?[2469]|11)\\-30|0?2\\-29|(?:0?[0-9]|1[012])\\-(?:0?[1-9]|[12][1-9]))\\-(?:(?:19|2[0-9])?[0-9]{2})$";

    private static final long DAY_IN_MILLISECONDS = 86400000L;

    private static Date parse(String textDate, @NotNull String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = format.parse(textDate);
        } catch (ParseException e) {
            System.out.println(e);
        }
        return date;
    }

    public static Date parseFromISO8601(String textDate) {
        return parse(textDate, ISO8601_DATE_PATTERN);
    }

    public static Date parseFromLatinAmericanDate(String textDate) {
        return parse(textDate, LATIN_AMERICAN_DATE_PATTERN);
    }

    public static Date parseFromNorthAmericanDate(String textDate) {
        return parse(textDate, NORTH_AMERICAN_DATE_PATTERN);
    }

    public static Date parseToDate(String textDate) {
        Date date = null;

        if (textDate.matches(ISO8601_DATE_REGEXP)) {
          date = parseFromISO8601(textDate);
        } else if (textDate.matches(LATIN_AMERICAN_DATE_REGEXP)) {
            date = parseFromLatinAmericanDate(textDate);
        } else if (textDate.matches(NORTH_AMERICAN_DATE_REGEXP)) {
            date = parseFromNorthAmericanDate(textDate);
        }

        return date;
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
