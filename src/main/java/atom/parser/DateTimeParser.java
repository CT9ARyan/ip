package atom.parser;

import atom.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {

    public static boolean isValidDateTime(String date, Ui ui) throws
            ArrayIndexOutOfBoundsException, NumberFormatException{
        //format example: "12/11/2024 17:30"
        date = date.trim();
        String[] dateTimeParams = date.split(" ");

        String[] timeParams = dateTimeParams[1].split(":");
        String hourString = timeParams[0].trim();
        String minString = timeParams[1].trim();

        String[] dateParams = dateTimeParams[0].split("/");
        String dayString = dateParams[0].trim();
        String monthString = dateParams[1].trim();
        String yearString = dateParams[2].trim();

        int hour = Integer.parseInt(hourString);
        int min = Integer.parseInt(minString);

        int day = Integer.parseInt(dayString);
        int month = Integer.parseInt(monthString);
        int year = Integer.parseInt(yearString);

        boolean isValidDate = isValidDay(day, ui) && isValidMonth(month, ui) && isValidYear(year, ui);
        boolean isValidTime = isValidHour(hour, ui) && isValidMinute(min, ui);

        return isValidDate && isValidTime;
    }

    public static boolean isValidDate(String date, Ui ui) throws
            ArrayIndexOutOfBoundsException, NumberFormatException{
        //format example: "12/11/2024"
        date = date.trim();

        String[] dateParams = date.split("/");
        String dayString = dateParams[0].trim();
        String monthString = dateParams[1].trim();
        String yearString = dateParams[2].trim();

        int day = Integer.parseInt(dayString);
        int month = Integer.parseInt(monthString);
        int year = Integer.parseInt(yearString);

        boolean isValidDate = isValidDay(day, ui) && isValidMonth(month, ui) && isValidYear(year, ui);
        return isValidDate;
    }

    private static boolean isValidDay(int day, Ui ui) {
        if (day < 1 || day > 31) {
            ui.showInvalidDayMessage();
            return false;
        }
        return true;
    }

    private static boolean isValidMonth(int month, Ui ui) {
        if (month < 1 || month > 12) {
            ui.showInvalidMonthMessage();
            return false;
        }
        return true;
    }

    private static boolean isValidYear(int year, Ui ui) {
        if (year < 2024) {
            ui.showInvalidYearMessage();
            return false;
        }
        return true;
    }

    private static boolean isValidHour(int hour, Ui ui) {
        if (hour < 0 || hour > 24) {
            ui.showInvalidHourMessage();
            return false;
        }
        return true;
    }

    private static boolean isValidMinute(int min, Ui ui) {
        if (min < 0 || min > 60) {
            ui.showInvalidMinuteMessage();
            return false;
        }
        return true;
    }

    public static String parseDateTime(String dateTime) throws DateTimeParseException {
        //format example: "12/11/2024 17:30"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
        String parsedDateTime = localDateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mma"));
        return parsedDateTime;
    }
}
