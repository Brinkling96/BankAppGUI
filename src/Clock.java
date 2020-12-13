import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Clock {
    private static Clock clock = new Clock();
    private Clock() { }

    public static Clock getClock() {
        return clock;
    }

    public String getTime() {
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
        return time.format(formatter);
    }
}
