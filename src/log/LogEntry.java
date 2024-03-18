package finalproject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogEntry {

    private LocalDateTime timestamp;
    private String user;
    private String event;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LogEntry(LocalDateTime timestamp, String user, String event) {
        this.timestamp = timestamp;
        this.user = user;
        this.event = event;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s: %s", timestamp.format(formatter), user, event);
    }
}

