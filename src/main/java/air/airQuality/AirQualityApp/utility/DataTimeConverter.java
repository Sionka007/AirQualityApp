package air.airQuality.AirQualityApp.utility;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DataTimeConverter {
    public static LocalDateTime unixToLocalDateTime(long unixTimestamp) {
        Instant instant = Instant.ofEpochSecond(unixTimestamp);
        return LocalDateTime.ofInstant(instant, ZoneId.of("UTC"));
    }

    public static long localDateTimeToUnix(LocalDateTime localDateTime) {
        Instant instant = localDateTime.atZone(ZoneId.of("UTC")).toInstant();
        return instant.getEpochSecond();
    }
}
