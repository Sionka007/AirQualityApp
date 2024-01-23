package air.airQuality.AirQualityApp.Controler;

import air.airQuality.AirQualityApp.DTO.CityDTO;
import air.airQuality.AirQualityApp.Services.CityService;
import air.airQuality.AirQualityApp.utility.AirQualityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/city")
public class CityController {

    @Autowired
    CityService cityService;



    @GetMapping("/{cityName}")
    public ResponseEntity<CityDTO> getCity(@PathVariable String cityName) throws AirQualityException {
        CityDTO cityDTO=cityService.findCityByName(cityName);

        return ResponseEntity.ok(cityDTO);
    }

    @GetMapping("/{lat}/{lon}")
    public ResponseEntity<CityDTO> getCityByLatitudeAndLongitude(@PathVariable String lat, @PathVariable String lon) throws AirQualityException {
        CityDTO cityDTO=cityService.findByLatitudeAndLongitude(Double.valueOf(lat), Double.valueOf(lon));

        return ResponseEntity.ok(cityDTO);
    }

    @GetMapping("/list/{country}")
    public ResponseEntity<List<CityDTO>> getCityListByCountry(@PathVariable String country) throws AirQualityException {
        List<CityDTO> cityDTOS=cityService.findCityByCountry(country);

        return ResponseEntity.ok(cityDTOS);
    }

    @PostMapping("/{cityName}")
    public ResponseEntity<String> addNewCity(@PathVariable String cityName) throws AirQualityException {
        String msg= "Successfully added new city with id: "+cityService.addCity(cityName);

        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }



    @DeleteMapping("/{city}")
    public ResponseEntity<Long> deleteByCityName(@PathVariable String city){
        Long id= cityService.deleteByCityName(city);

        return ResponseEntity.ok(id);
    }

    @GetMapping("/cities")
    public String showCities(Model model) {
        List<CityDTO> cityDTOS = cityService.findAllCity();

        List<String> cityNames = new ArrayList<>();

        cityDTOS.forEach(cityDTO -> cityNames.add(cityDTO.getCityName()));

        model.addAttribute("cityNames", cityNames);
        return "city-list";
    }
}
