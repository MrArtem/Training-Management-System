package com.exadel.training.utils;


import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Utils {

    public static <T> Iterable<T> emptyIfNull(Iterable<T> iterable) {
        return iterable == null ? Collections.<T>emptyList() : iterable;
    }

    public static Long getTime() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        return calendar.getTimeInMillis();
    }

    public static int getDayOfWeek(Long millis) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.setTimeInMillis(millis);
        return (calendar.get(Calendar.DAY_OF_WEEK) + 5) % 7;
    }
}
