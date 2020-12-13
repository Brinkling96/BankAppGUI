import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Clock {
    private static Clock clock = new Clock();
    private Clock() { }

    public static Clock getClock() {
        return clock;
    }

    public LocalDateTime getTime() {
        return LocalDateTime.now();
    }

    public String getTimeAsString(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
        return time.format(formatter);
    }

    public Long getTimeAsLong(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyyHHmmss");
        return Long.parseLong(time.format(formatter));
    }
}
