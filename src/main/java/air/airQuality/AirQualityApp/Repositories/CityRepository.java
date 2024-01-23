package air.airQuality.AirQualityApp.Repositories;

import air.airQuality.AirQualityApp.Entities.CityData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CityRepository extends CrudRepository<CityData, Long> {
    CityData findByCityName(String name);
    CityData findByLatitudeAndLongitude(Double latitude, Double longitude);
    List<CityData> findByCountry(String country);
    Long deleteByCityName(String name);
}
