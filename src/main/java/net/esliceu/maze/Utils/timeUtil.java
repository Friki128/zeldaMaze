package net.esliceu.maze.Utils;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class timeUtil {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    static public String getTime(){
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }
    static public String calcTimeDistance(String startTime, String endTime){
        LocalDateTime startLocal = LocalDateTime.parse(startTime, formatter);
        LocalDateTime endLocal = LocalDateTime.parse(endTime, formatter);
        Duration duration = Duration.between(startLocal, endLocal);
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;
        StringBuilder timeDistance = new StringBuilder();
        if (hours > 0) {
            timeDistance.append(hours).append(" hours ");
        }
        if (minutes > 0) {
            timeDistance.append(minutes).append(" minutes ");
        }
        if (seconds > 0) {
            timeDistance.append(seconds).append(" seconds");
        }
        return timeDistance.toString().trim();
    }
}
