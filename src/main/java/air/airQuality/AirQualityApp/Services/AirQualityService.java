package air.airQuality.AirQualityApp.Services;

import air.airQuality.AirQualityApp.DTO.AirQualityDataDTO;
import air.airQuality.AirQualityApp.DTO.CityDTO;
import air.airQuality.AirQualityApp.Entities.AirQualityData;
import air.airQuality.AirQualityApp.Entities.AirQualityIndex;
import air.airQuality.AirQualityApp.Entities.CityData;
import air.airQuality.AirQualityApp.Repositories.AirQualityRepository;
import air.airQuality.AirQualityApp.Repositories.CityRepository;
import air.airQuality.AirQualityApp.utility.AirQualityException;
import air.airQuality.AirQualityApp.utility.DataTimeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
@Transactional
public class AirQualityService {

    @Autowired
    AirQualityRepository airQualityRepository;

    @Autowired
    CityRepository cityRepository;
    @Autowired
    CityService cityService;

    private final String openWeatherMapApiKey = "0ed0adbb801e2c9f4ff4e496324a5ef0";

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept", "application/json;charset=UTF-8");
        return headers;
    }

    public List<AirQualityDataDTO> findByTimestampBetweenAndCityName(LocalDateTime start, LocalDateTime end,
                                                                     String cityName) throws AirQualityException {
        List<AirQualityData> airQualityDataList=airQualityRepository.findByTimestampBetweenAndCityName(start, end, cityName);

        if (!airQualityDataList.isEmpty()){
            List<AirQualityDataDTO> dataDTOS=new ArrayList<>();

            for(AirQualityData airQualityData : airQualityDataList){
                CityDTO cityDTO=cityService.findCityByName(cityName);

                AirQualityDataDTO dataDTO=new AirQualityDataDTO(airQualityData.getTimestamp(), airQualityData.getAirQualityIndex(),
                        airQualityData.getCo(), airQualityData.getNo(), airQualityData.getNo2(), airQualityData.getO3(),
                        airQualityData.getSo2(), airQualityData.getPm2_5(), airQualityData.getPm10(), airQualityData.getNh3(),
                        cityDTO);

                dataDTOS.add(dataDTO);
            }
            return dataDTOS;
        }else{
            throw new AirQualityException("No find any air quality data for city or date");
        }
    }

    public List<AirQualityDataDTO> findByCityName(String cityName) throws AirQualityException {
        List<AirQualityData> dataList=airQualityRepository.findByCityName(cityName);

        if (!dataList.isEmpty()){
            List<AirQualityDataDTO> dtoList=new ArrayList<>();
            for(AirQualityData data : dataList){
                CityDTO cityDTO=cityService.findCityByName(cityName);
                AirQualityDataDTO dataDTO=new AirQualityDataDTO(data.getTimestamp(), data.getAirQualityIndex(),
                        data.getCo(), data.getNo(), data.getNo2(), data.getO3(), data.getSo2(), data.getPm2_5(),
                        data.getPm10(), data.getNh3(), cityDTO);

                dtoList.add(dataDTO);
            }
            return dtoList;
        }else{
            throw new AirQualityException("No find any air quality data for city or date");
        }
    }

    public void addDataByCityName(String cityName) throws AirQualityException {
        Long id=cityService.addCity(cityName);
        Optional<CityData> optional=cityRepository.findById(id);
        CityData cityData=optional.orElseThrow(() -> new AirQualityException("No city found"));


        Long todayDate = DataTimeConverter.localDateTimeToUnix(LocalDateTime.now());
        Long oneMonthBefore = DataTimeConverter.localDateTimeToUnix(LocalDateTime.now().minusMonths(2));

        String openWeatherMapApiUrl = "http://api.openweathermap.org/data/2.5/air_pollution/history?" +
                "lat={lat}&lon={lon}&start={oneMonthBefore}&end={todayDate}&appid=" + openWeatherMapApiKey;




        String apiUrl = openWeatherMapApiUrl.replace("{lat}", String.valueOf(cityData.getLatitude())).
                replace("{lon}", String.valueOf(cityData.getLongitude())).
                replace("{oneMonthBefore}", String.valueOf(oneMonthBefore))
                .replace("{todayDate}", String.valueOf(todayDate));

        HttpHeaders headers = createHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, Map.class);

        Map<String, Object> responseMap = responseEntity.getBody();

        if (responseMap != null && responseMap.containsKey("list")) {
            List<Map<String, Object>> airQualityList = (List<Map<String, Object>>) responseMap.get("list");

            if (airQualityList != null && !airQualityList.isEmpty() ) {
                for (Map<String, Object> airQualityMap : airQualityList) {
                    if (airQualityMap != null && airQualityMap.containsKey("main") && airQualityMap.containsKey("components")) {
                        Map<String, Object> mainMap = (Map<String, Object>) airQualityMap.get("main");
                        Map<String, Object> componentsMap = (Map<String, Object>) airQualityMap.get("components");
                        Integer dtMap = (Integer) airQualityMap.get("dt");

                        AirQualityData airQuality = new AirQualityData();
                        airQuality.setAirQualityIndex(AirQualityIndex.getByValue((Integer) mainMap.get("aqi")));
                        airQuality.setCo(componentsMap.get("co") instanceof Double ? (Double) componentsMap.get("co") : ((Integer) componentsMap.get("co")).doubleValue());
                        airQuality.setNo(componentsMap.get("no") instanceof Double ? (Double) componentsMap.get("no") : ((Integer) componentsMap.get("no")).doubleValue());
                        airQuality.setNo2(componentsMap.get("no2") instanceof Double ? (Double) componentsMap.get("no2") : ((Integer) componentsMap.get("no2")).doubleValue());
                        airQuality.setO3(componentsMap.get("o3") instanceof Double ? (Double) componentsMap.get("o3") : ((Integer) componentsMap.get("o3")).doubleValue());
                        airQuality.setSo2(componentsMap.get("so2") instanceof Double ? (Double) componentsMap.get("so2") : ((Integer) componentsMap.get("so2")).doubleValue());
                        airQuality.setPm2_5(componentsMap.get("pm2_5") instanceof Double ? (Double) componentsMap.get("pm2_5") : ((Integer) componentsMap.get("pm2_5")).doubleValue());
                        airQuality.setPm10(componentsMap.get("pm10") instanceof Double ? (Double) componentsMap.get("pm10") : ((Integer) componentsMap.get("pm10")).doubleValue());
                        airQuality.setNh3(componentsMap.get("nh3") instanceof Double ? (Double) componentsMap.get("nh3") : ((Integer) componentsMap.get("nh3")).doubleValue());
                        airQuality.setTimestamp(DataTimeConverter.unixToLocalDateTime(dtMap));
                        airQuality.setCity(cityData);

                        airQualityRepository.save(airQuality);
                    } else {
                        throw new AirQualityException("Invalid response format");
                    }
                }
            } else {
                throw new AirQualityException("No air quality data available");
            }
        } else {
            throw new AirQualityException("Invalid response format");
        }
    }

    public List<String> getMinMaxDates(String cityName) {
        LocalDateTime min = airQualityRepository.findMinTimestampByCityName(cityName);
        LocalDateTime max = airQualityRepository.findMaxTimestampByCityName(cityName);

        if (min !=null && max != null) {
            List<String> minMaxDates = new ArrayList<>();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

            minMaxDates.add(min.format(formatter));
            minMaxDates.add(max.format(formatter));


            return minMaxDates;
        } else {
            throw new AirQualityException("No date min or max found in Service");
        }
    }


}
