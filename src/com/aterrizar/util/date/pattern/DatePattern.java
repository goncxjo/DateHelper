package com.aterrizar.util.date.pattern;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class DatePattern {
    protected String DATE_PATTERN;

    abstract String buildDateFormat(String textDate);

    String format(String textDate) {
        Pattern pattern = Pattern.compile("[^0-9]");
        Matcher matcher = pattern.matcher(textDate);

        String textDateWithoutSeparators = matcher.replaceAll("");
        return buildDateFormat(textDateWithoutSeparators);
    }

    public Date parse(String textDate) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
        textDate = format(textDate);
        Date date = null;
        try {
            date = format.parse(textDate);
        } catch (ParseException e) {
            System.out.println(e);
        }
        return date;
    }
}
