import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Clock {
    public static String TIME_PATTERN = "MM-dd-yyyy HH:mm:ss";
    private static Clock clock = new Clock();
    private DateTimeFormatter formatter;
    private Clock() { 
        formatter = DateTimeFormatter.ofPattern(TIME_PATTERN); 
    }

    public static Clock getClock() {
        return clock;
    }

    public LocalDateTime getTime() {
        return LocalDateTime.now();
    }

    public String getTimeAsString(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_PATTERN);
        return time.format(formatter);
    }

    public Long getTimeAsLong(LocalDateTime time) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("MMddyyyy");
        return Long.parseLong(time.format(f));
    }

    public long daysElapsed(LocalDateTime start, LocalDateTime end) {
        Duration dur = Duration.between(start, end);
        long days = dur.toDays();
        return days;
    }

    public LocalDateTime getLocalDateTimeFromString(String time) {
        return LocalDateTime.parse(time, formatter);
    }
}
