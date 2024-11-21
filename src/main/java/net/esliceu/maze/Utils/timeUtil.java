package net.esliceu.maze.Utils;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Date;

public class timeUtil {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static public String getTime(){
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }
    static public String calcTimeDistance(String startTime, String endTime){
        LocalDateTime startLocal = LocalDateTime.parse(startTime);
        LocalDateTime endLocal = LocalDateTime.parse(endTime);
        Duration duration = Duration.between(startLocal, endLocal);
        return duration.toString();
    }
}
