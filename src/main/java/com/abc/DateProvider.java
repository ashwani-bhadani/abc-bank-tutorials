package com.abc;

import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

public class DateProvider {
    private static DateProvider instance = null;

    public static DateProvider getInstance() {
        if (instance == null)
            instance = new DateProvider();
        return instance;
    }

    public ZonedDateTime now() {
        return ZonedDateTime.now();
    }
}
