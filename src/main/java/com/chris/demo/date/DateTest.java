package com.chris.demo.date;

import cn.hutool.core.date.DatePattern;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * 首先你需要确定数据对象中的 Date 字段代表的是日期、时间还是时间戳。
 * <p>
 * 如果字段代表日期和时间，则可能需要使用 LocalDateTime。
 * 如果字段仅代表日期，则可能需要使用 LocalDate。
 * 如果字段仅代表时间，则可能需要使用 LocalTime。
 * 如果字段需要保存时间戳（带时区的），则可能需要使用 Instant 或 ZonedDateTime。
 */
public class DateTest {


    /**
     * Date nowDate = new Date();
     * Date nowCalendarDate = Calendar.getInstance().getTime();
     * <p>
     * 1. Instant 表示的是一个时间点，它是时区无关的，相当于旧的 Date 类。它通常用于表示时间戳。
     * 2. LocalDateTime 表示没有时区信息的日期和时间，它不能直接转换为时间戳，除非你将其与时区结合使用（例如通过 ZonedDateTime）。
     * 3. ZonedDateTime 包含时区信息的日期和时间，它更类似于 Calendar，因为 Calendar 也包含时区信息。
     * 4. 当你需要将 java.time 对象转换回 java.util.Date 对象时，可以使用 Date.from(Instant) 方法。这在你的代码需要与旧的API或库交互时非常有用。
     */
    @Test
    public void test01() {
        // 使用Instant代表一个时间点，这与Date类似
        Instant nowInstant = Instant.now();

        // 如果需要用到具体的日期和时间（例如年、月、日、时、分、秒）
        LocalDateTime nowLocalDateTime = LocalDateTime.now();

        // 如果你需要和特定的时区交互，可以使用ZonedDateTime
        ZonedDateTime nowZonedDateTime = ZonedDateTime.now();

        // 如果你需要转换回java.util.Date，你可以这样做（假设你的代码其他部分还需要使用Date）
        Date nowFromDateInstant = Date.from(nowInstant);

        // 如果需要与java.sql.Timestamp交互
        Timestamp nowFromInstant = Timestamp.from(nowInstant);
    }


    @Test
    public void test02() {
        String s = dateFormat(LocalDateTime.now(), DatePattern.NORM_DATETIME_MS_PATTERN);
        System.out.println(s);
    }

    public static String dateFormat_old(Date date, String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(date);
    }

    private static String dateFormat(LocalDateTime date, String dateFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        return date.format(formatter);
    }


    /**
     * 2024-05-26T11:08:44.717
     * 2025-06-27T12:09:44.717
     */
    @Test
    public void test03() {
        LocalDateTime date = LocalDateTime.now();
        System.out.println(date);

        LocalDateTime localDateTime = date.plusMinutes(1).plusHours(1).plusDays(1).plusMonths(1).plusYears(1);
        System.out.println(localDateTime);
    }

    public static final String[] WEEK_DAY_OF_CHINESE = new String[]{"周日", "周一", "周二", "周三", "周四", "周五", "周六"};

    public static String dateToWeek_old(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return WEEK_DAY_OF_CHINESE[cal.get(7) - 1];
    }

    @Test
    public void dateToWeek() {
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        String s = WEEK_DAY_OF_CHINESE[dayOfWeek.getValue() % 7];
        System.out.println(s);
    }

    @Test
    public void test04() {
        LocalDateTime date = LocalDateTime.of(2023, Month.JANUARY, 1, 12, 0, 0);
        LocalDateTime startTimeOfDay = getStartTimeOfDay(date);
        LocalDateTime endTimeOfDay = getEndTimeOfDay(date);
        System.out.println(startTimeOfDay);
        System.out.println(endTimeOfDay);
    }

    /**
     * 获取一天的开始时间，即00:00
     */
    private static LocalDateTime getStartTimeOfDay(LocalDateTime date) {
        if (date == null) {
            return null;
        } else {
            return date.toLocalDate().atStartOfDay();
        }
    }

    /**
     * 获取一天的结束时间，即23:59:59.999999999
     */
    private static LocalDateTime getEndTimeOfDay(LocalDateTime date) {
        if (date == null) {
            return null;
        } else {
            return date.toLocalDate().atTime(LocalTime.MAX);
        }
    }

    @Test
    public void test05() {
        Instant beginTime = LocalDateTime.of(2024, Month.JANUARY, 1, 12, 0, 0).toInstant(ZoneOffset.UTC);
        Instant endTime = LocalDateTime.of(2024, Month.OCTOBER, 7, 12, 0, 0).toInstant(ZoneOffset.UTC);
        Instant nowTime = Instant.now();
        System.out.println(betweenStartAndEnd(nowTime, beginTime, endTime));
    }

    private static Boolean betweenStartAndEnd(Instant nowTime, Instant beginTime, Instant endTime) {
        return nowTime.isAfter(beginTime) && nowTime.isBefore(endTime);
    }


}
