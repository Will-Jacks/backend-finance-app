package com.app.finance.utils;

import java.time.LocalDate;

public class DateUtils {

    public static LocalDate getStartOfCurrentMonth() {
        return LocalDate.now().withDayOfMonth(1);
    }

    public static LocalDate getEndOfCurrentMonth() {
        LocalDate now = LocalDate.now();
        return now.withDayOfMonth(now.lengthOfMonth());
    }
;}
