package com.aterrizar.util.date.pattern;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import static org.junit.Assert.*;

public class DatePatternTest {

    @Test
    public void format_ISO8601_OnlyNumbers() {
        DatePattern datePattern = new ISO8601();
        String textDate = "20010101";
        String formattedTextDate = datePattern.format(textDate);
        assertTrue("formatted date doesn't match: " + formattedTextDate, formattedTextDate.equals("2001-01-01"));
    }

    @Test
    public void format_ISO8601_WithSeparators() {
        DatePattern datePattern = new ISO8601();
        String textDate = "2001-01-01";
        String formattedTextDate = datePattern.format(textDate);
        assertTrue("formatted date doesn't match: " + formattedTextDate, formattedTextDate.equals(textDate));
    }

    @Test
    public void format_ISO8601_WithMissingSeparator() {
        DatePattern datePattern = new ISO8601();
        String textDate = "2001-0101";
        String formattedTextDate = datePattern.format(textDate);
        assertTrue("formatted date doesn't match: " + formattedTextDate, formattedTextDate.equals("2001-01-01"));
    }

    @Test
    public void format_ISO8601_WithInvalidSeparator() {
        DatePattern datePattern = new ISO8601();
        String textDate = "2001/01/01";
        String formattedTextDate = datePattern.format(textDate);
        assertTrue("formatted date doesn't match: " + formattedTextDate, formattedTextDate.equals("2001-01-01"));
    }

    @Test
    public void parse_ToISO8601_WithMissingSeparator() {
        DatePattern datePattern = new ISO8601();
        String dateText = "20010101";
        Date outputDate = datePattern.parse(dateText);

        Calendar expectedDate = Calendar.getInstance();
        expectedDate.clear();
        expectedDate.set(2001, Calendar.JANUARY, 1);

        assertTrue("The dates aren't equal", expectedDate.getTime().equals(outputDate));
    }
}