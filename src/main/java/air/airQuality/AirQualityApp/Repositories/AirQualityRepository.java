package air.airQuality.AirQualityApp.Repositories;

import air.airQuality.AirQualityApp.Entities.AirQualityData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AirQualityRepository extends CrudRepository<AirQualityData, Long> {
    @Query("SELECT a " +
            "FROM AirQualityData a INNER JOIN a.city c " +
            "WHERE a.timestamp BETWEEN :start AND :end " +
            "AND LOWER(c.cityName) = LOWER(:cityName)")
    List<AirQualityData> findByTimestampBetweenAndCityName(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            @Param("cityName") String cityName);
    @Query("SELECT a FROM AirQualityData a WHERE LOWER(a.city.cityName) = LOWER(:cityName)")
    List<AirQualityData> findByCityName(@Param("cityName") String cityName);

    @Query("SELECT MIN(a.timestamp) " +
            "FROM AirQualityData a INNER JOIN a.city c " +
            "WHERE LOWER(c.cityName) = LOWER(:cityName)")
    LocalDateTime findMinTimestampByCityName(@Param("cityName") String cityName);

    @Query("SELECT MAX(a.timestamp) " +
            "FROM AirQualityData a INNER JOIN a.city c " +
            "WHERE LOWER(c.cityName) = LOWER(:cityName)")
    LocalDateTime findMaxTimestampByCityName(@Param("cityName") String cityName);
}
