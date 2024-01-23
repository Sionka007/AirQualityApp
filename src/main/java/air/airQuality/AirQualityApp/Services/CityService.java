package air.airQuality.AirQualityApp.Services;

import air.airQuality.AirQualityApp.DTO.CityDTO;
import air.airQuality.AirQualityApp.Entities.CityData;
import air.airQuality.AirQualityApp.Repositories.CityRepository;
import air.airQuality.AirQualityApp.utility.AirQualityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CityService {

    @Autowired
    CityRepository cityRepository;

    private final String openWeatherMapApiKey = "0ed0adbb801e2c9f4ff4e496324a5ef0";

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept", "application/json;charset=UTF-8");
        return headers;
    }

    public CityDTO findCityByName(String name) throws AirQualityException {
        CityData cityData=cityRepository.findByCityName(name);

        if(cityData!=null){
            CityDTO cityDTO = new CityDTO(cityData.getCityName(), cityData.getLatitude(),
                    cityData.getLongitude(), cityData.getCountry());

            return cityDTO;
        }else {
            throw new AirQualityException("No city found");
        }
    }

    public CityDTO findByLatitudeAndLongitude(Double latitude, Double longitude) throws AirQualityException {
        String openWeatherMapApiUrl ="http://api.openweathermap.org/geo/1.0/reverse?lat={lat}&lon={lon}&appid="
                +openWeatherMapApiKey;
        String apiUrl = openWeatherMapApiUrl.replace("{lat}", String.valueOf(latitude)).replace("{lon}", String.valueOf(longitude));

        HttpHeaders headers = createHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Map<String, Object>>> responseEntity = restTemplate.exchange(
                apiUrl, HttpMethod.GET, entity,
                new ParameterizedTypeReference<>() {
                });

        List<Map<String, Object>> cityList = responseEntity.getBody();

        if (cityList != null && !cityList.isEmpty()) {
            Map<String, Object> cityMap = cityList.get(0);

            if (cityMap != null && !cityMap.isEmpty()) {
                // Bezpośrednie mapowanie do CityDTO
                CityDTO cityDTO = new CityDTO();
                cityDTO.setCityName((String) cityMap.get("name")); // Zmiana z "name" na dokładną nazwę klucza w odpowiedzi
                cityDTO.setCountry((String) cityMap.get("country"));
                cityDTO.setLatitude((Double) cityMap.get("lat"));
                cityDTO.setLongitude((Double) cityMap.get("lon"));

                return cityDTO;
            }else{
                throw new AirQualityException("Not found city");
            }
        } else {
            throw new AirQualityException("Not found city");
        }
    }

    public List<CityDTO> findCityByCountry(String country) throws AirQualityException {
        List<CityData> cities=cityRepository.findByCountry(country);

        if(!cities.isEmpty()){
            List<CityDTO> cityDTOS=new ArrayList<>();

            for(CityData city :cities){
                CityDTO cityDTO=new CityDTO(city.getCityName(), city.getLatitude(), city.getLongitude(), city.getCountry());

                cityDTOS.add(cityDTO);
            }
            return cityDTOS;
        }else {
            throw new AirQualityException("No cities found");
        }

    }

    public Long addCity(String cityName) throws AirQualityException {
        String openWeatherMapApiUrl = "http://api.openweathermap.org/geo/1.0/direct?q={city name}&appid="
                + openWeatherMapApiKey;

        String apiUrl = openWeatherMapApiUrl.replace("{city name}", cityName);

        HttpHeaders headers = createHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Map<String, Object>>> responseEntity = restTemplate.exchange(
                apiUrl, HttpMethod.GET, entity,
                new ParameterizedTypeReference<>() {
                });

        List<Map<String, Object>> cityList = responseEntity.getBody();
        CityDTO cityDTO = new CityDTO();

        if (cityList != null && !cityList.isEmpty()) {
            Map<String, Object> cityMap = cityList.get(0);

            if (cityMap != null && !cityMap.isEmpty()) {
                // Bezpośrednie mapowanie do CityDTO

                cityDTO.setCityName((String) cityMap.get("name")); // Zmiana z "name" na dokładną nazwę klucza w odpowiedzi
                cityDTO.setCountry((String) cityMap.get("country"));
                cityDTO.setLatitude((Double) cityMap.get("lat"));
                cityDTO.setLongitude((Double) cityMap.get("lon"));

            }else{
                throw new AirQualityException("Not found city");
            }
        } else {
            throw new AirQualityException("Not found city");
        }

        CityData cityData=new CityData();

        cityData.setCityName(cityDTO.getCityName());
        cityData.setLatitude(cityDTO.getLatitude());
        cityData.setLongitude(cityDTO.getLongitude());
        cityData.setCountry(cityDTO.getCountry());

        cityRepository.save(cityData);
        Long newId=cityData.getCityId();

        return newId;
    }

    public Long deleteByCityName(String name){
        Long id=cityRepository.deleteByCityName(name);

        return id;
    }

    public List<CityDTO> findAllCity(){
        Iterable<CityData> iterable= cityRepository.findAll();
        List<CityData> dataList= new ArrayList<>();

        iterable.forEach(dataList :: add);

        if(!dataList.isEmpty()){
            List<CityDTO>  dtoList=new ArrayList<>();

            for(CityData cityData : dataList){
                CityDTO cityDTO= new CityDTO(cityData.getCityName(), cityData.getLatitude(),
                        cityData.getLongitude(), cityData.getCountry());

                dtoList.add(cityDTO);
            }
            return dtoList;
        }else {
            throw new AirQualityException("No cities found");
        }
    }
}
