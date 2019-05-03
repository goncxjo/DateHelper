package com.aterrizar.util;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class DateHelperTest {

    @Test
    public void parseFromISO8601() {
        String dateText = "2018-12-09";
        Date outputDate = DateHelper.parseFromISO8601(dateText);

        Calendar expectedDate = Calendar.getInstance();
        expectedDate.clear();
        expectedDate.set(2018, Calendar.DECEMBER, 9);

        assertTrue("The dates aren't equal", expectedDate.getTime().equals(outputDate));
    }

    @Test
    public void parseFromLatinAmericanDate() {
        String dateText = "14/03/2018";
        Date outputDate = DateHelper.parseFromLatinAmericanDate(dateText);

        Calendar expectedDate = Calendar.getInstance();
        expectedDate.clear();
        expectedDate.set(2018, Calendar.MARCH, 14);

        assertTrue("The dates aren't equal", expectedDate.getTime().equals(outputDate));
    }

    @Test
    public void parseFromNorthAmericanDate() {
        String dateText = "11-27-2017";
        Date outputDate = DateHelper.parseFromNorthAmericanDate(dateText);

        Calendar expectedDate = Calendar.getInstance();
        expectedDate.clear();
        expectedDate.set(2017, Calendar.NOVEMBER, 27);

        assertTrue("The dates aren't equal", expectedDate.getTime().equals(outputDate));
    }

    @Test
    public void parseToDate() {
        String dateText = "01-01-2001";
        Date outputDate = DateHelper.parseToDate(dateText);

        Calendar expectedDate = Calendar.getInstance();
        expectedDate.clear();
        expectedDate.set(2001, Calendar.JANUARY, 1);

        assertTrue("The dates aren't equal", expectedDate.getTime().equals(outputDate));
    }

    @Test(expected = PatternDoesntMatchException.class)
    public void parseToDate_PatternException() {
        String dateText = "01-01-20-01";
        Date outputDate = DateHelper.parseToDate(dateText);
    }

    @Test
    public void thereIsOneDayOfDifference() {
        Date dateOne = DateHelper.parseToDate("24/12/2014");
        Date dateTwo = DateHelper.parseToDate("25/12/2014");

        assertEquals("There isn't one day of difference", 1, DateHelper.calculateDaysDifference(dateOne, dateTwo));
    }

    @Test
    public void thereIsThirtyDaysOfDifference() {
        Date dateOne = DateHelper.parseToDate("24/12/2014");
        Date dateTwo = DateHelper.parseToDate("24/11/2014");

        assertEquals("There isn't thirty days of difference", 30, DateHelper.calculateDaysDifference(dateOne, dateTwo));
    }

    @Test
    public void thereIsAHundredDaysOfDifference() {
        Date dateOne = DateHelper.parseToDate("01/01/2019");
        Date dateTwo = DateHelper.parseToDate("11/04/2019");

        assertEquals("There isn't a hundred days of difference", 100, DateHelper.calculateDaysDifference(dateOne, dateTwo));
    }

    @Test
    public void ChristmasIsBeforeNewYear() {
        Date dateOne = DateHelper.parseToDate("25/12/1993");
        Date dateTwo = DateHelper.parseToDate("01/01/1994");

        assertTrue("Christmas isn't before New Year", DateHelper.isBefore(dateOne, dateTwo));
    }

    @Test
    public void equalDates_firstDateIsNotBeforeSecondDate() {
        Date dateOne = DateHelper.parseToDate("24/12/2014");
        Date dateTwo = DateHelper.parseToDate("24/12/2014");

        assertFalse("Is The first date before the second date?", DateHelper.isBefore(dateOne, dateTwo));
    }
}