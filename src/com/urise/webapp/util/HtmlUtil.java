package com.urise.webapp.util;

import com.urise.webapp.model.Company;

import java.time.LocalDate;

public class HtmlUtil {
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String formatDates(Company.Period position) {
        String endDate = isCurrentMonthYear(position) ? "Сейчас" : DateUtil.format(position.getEndDate());
        return DateUtil.format(position.getStartDate()) + " - " + endDate;
    }

    private static boolean isCurrentMonthYear(Company.Period position) {
        LocalDate currentDate = LocalDate.now();
        return currentDate.getYear() == position.getEndDate().getYear()
                && currentDate.getMonth() == position.getEndDate().getMonth();
    }
}
