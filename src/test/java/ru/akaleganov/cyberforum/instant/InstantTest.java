package ru.akaleganov.cyberforum.instant;

import java.sql.Time;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

@Log4j2
public class InstantTest {

    @Test
    public void testInstant() {
        Instant instant = Instant.now();
        var r = LocalDate.now().atTime(0, 0);
        log.info(r.atZone(ZoneId.of("Europe/Moscow")).toInstant());
        log.info(instant.truncatedTo(ChronoUnit.DAYS).plus(1, ChronoUnit.DAYS));

        String timeZone = "America/Los_Angeles";
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ZoneId zoneId1 = ZoneId.of(timeZone);
        String dateStr = "2017-03-03T13:14:28.666Z";
    }

    @Test
    public void testTime() {
        Instant instantStart = Instant.now();
        Instant instantEnd = Instant.now();
        log.info("instantStart = {}", instantStart);
        log.info("instantEnd = {}", instantEnd);
    }

    public Period getPeriodShiftFromInstant(Time start, Time end, Instant dateTime) {
        Period period = Period.builder().startPeriod(dateTime).endPeriod(dateTime).build();
        period.setStartPeriod(dateTime.truncatedTo(ChronoUnit.DAYS)
                                      .plus(getHourFromTime(start), ChronoUnit.HOURS)
                                      .plus(getMinuteFromTime(end), ChronoUnit.MINUTES));
        period.setEndPeriod(period.getStartPeriod().plus(getMinutePeriod(start, end), ChronoUnit.MINUTES));
        return period;
    }

    private int getHourFromTime(Time time) {
        return time.toLocalTime()
                   .get(ChronoField.HOUR_OF_DAY);

    }

    private int getMinuteFromTime(Time time) {
        return time.toLocalTime()
                   .get(ChronoField.MINUTE_OF_HOUR);
    }

    private int getMinutePeriod(Time start, Time end) {
        Duration duration = Duration.between(
                start.toLocalTime(),
                end.toLocalTime());
        return (int) duration.toMinutes();
    }

    /**
     * Фильтрует смены по времени.
     *
     * @param e
     *         {@link ShiftDto}
     * @param dateTime
     *         {Дата и время, которое возможно входит в какую-то смену}
     *
     * @return вернёт true, если
     */
    private boolean isContainsTimeInShift(ShiftDto e, Instant dateTime) {
        Period period = getPeriodShiftFromInstant(e, dateTime);
        return period.getStartPeriod().isBefore(dateTime) && period.getEndPeriod().isAfter(dateTime);
    }

}
