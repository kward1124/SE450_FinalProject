package finalproject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EntryLogger implements Logger {

    // Create a list to store log entries
    private List<LogEntry> logEntries;
    private static final String LOG_FILE_PATH = "application.log";

    public EntryLogger() {
        this.logEntries = new ArrayList<>();
    }

    @Override
    // This method logs an event
    public void log(String userId, String user, String event) {
        LogEntry entry = new LogEntry(LocalDateTime.now(), user, event);
        logEntries.add(entry);
        writeLogToFile(entry);
    }

    private void writeLogToFile(LogEntry entry){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true))){
            writer.write(entry.toString());
            writer.newLine();
        } catch (IOException e){
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }


    // This method returns a list of log entries
    public List<LogEntry> getLogEntries() {
        return new ArrayList<>(logEntries); // Return a copy to preserve encapsulation
    }

    public void printLogEntries(){
        for(LogEntry entry : logEntries){
            System.out.println(entry);
        }
    }
}

