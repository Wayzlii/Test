package otus.util;

import java.time.LocalDate;
import java.time.Month;

public class DateUtil {

    //Необходимо для каждой даты приводить месяц в именительный падеж. Первый вариант приведения даты к LocalDate выглядит для меня чуть проще

    //    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL, yyyy", Locale.forLanguageTag("RU"));
    //    public static LocalDate getDate(String dateStr) {
    //        TemporalAccessor parse = formatter.parse(dateStr);
    //        return LocalDate.from(parse);
    //    }
    //    public static LocalDate getDate(String dateStr, int year) {
    //        TemporalAccessor parse = formatter.parse(dateStr + year);
    //        return LocalDate.from(parse);
    //    }

    public static LocalDate getDate(String dateStr) {
        String[] stringArr = dateStr.split(" ");
        String year = stringArr[2];
        if (year.matches("[0-9]*"))
            return getDate(dateStr, Integer.parseInt(year));
        return null;
    }


    public static LocalDate getDate(String dateStr, int year) {
        String[] stringArr = dateStr.split(" ");
        String day = stringArr[0];
        if (day.matches("[0-9]*")) {
            return LocalDate.of(year, monthNum(stringArr[1]), Integer.parseInt(day));
        }
        return null;
    }

    private static Month monthNum(String month) {
        return switch (month) {
            case "января" -> Month.JANUARY;
            case "февраля" -> Month.FEBRUARY;
            case "марта" -> Month.MARCH;
            case "апреля" -> Month.APRIL;
            case "мая" -> Month.MAY;
            case "июня" -> Month.JUNE;
            case "июля" -> Month.JULY;
            case "августа" -> Month.AUGUST;
            case "сентября" -> Month.SEPTEMBER;
            case "октября" -> Month.OCTOBER;
            case "ноября" -> Month.NOVEMBER;
            case "декабря" -> Month.DECEMBER;
            default -> throw new IllegalArgumentException("Переданное значение некорректно.");
        };
    }
}
