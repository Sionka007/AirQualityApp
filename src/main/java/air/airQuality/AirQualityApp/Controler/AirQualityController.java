package air.airQuality.AirQualityApp.Controler;

import air.airQuality.AirQualityApp.DTO.AirQualityDataDTO;
import air.airQuality.AirQualityApp.DTO.CityDTO;
import air.airQuality.AirQualityApp.Services.AirQualityService;
import air.airQuality.AirQualityApp.Services.CityService;
import air.airQuality.AirQualityApp.utility.AirQualityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/air")
public class AirQualityController {
    @Autowired
    AirQualityService airQualityService;

    @Autowired
    CityService cityService;


    @PostMapping("/add-city-new-data")
    public String addNewDataByCityName(@RequestParam String cityName) throws AirQualityException,
            UnsupportedEncodingException{

        airQualityService.addDataByCityName(cityName);

        return "redirect:/air/success";
    }

    @GetMapping("/{cityName}")
    public String getAirQualityByCityName(@PathVariable String cityName, Model model) throws AirQualityException{
        List<AirQualityDataDTO> dtoList = airQualityService.findByCityName(cityName);
        model.addAttribute("airQualityList", dtoList);
        model.addAttribute("cityName", cityName);
        return "air-quality-list";
    }


    @GetMapping("/filter-air-quality")
    public String findByTimestampBetweenAndCityName(
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam String city,
            Model model ) throws AirQualityException, UnsupportedEncodingException {

        LocalDate startDateTime = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endDateTime = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        LocalDateTime startDateTimeWithHour = startDateTime.atTime(0, 0);
        LocalDateTime endDateTimeWithHour = endDateTime.plusDays(1).atTime(23, 59);

        List<AirQualityDataDTO> dtoList = airQualityService.findByTimestampBetweenAndCityName(
                startDateTimeWithHour,
                endDateTimeWithHour,
                city
        );

        model.addAttribute("airQualityList", dtoList);
        model.addAttribute("cityName", city);


        return "air-quality-list";
    }

    @GetMapping("/filter-form")
    public String showFilterForm(Model model) {
        List<CityDTO> cityDTOS = cityService.findAllCity();

        List<String> cityNames = new ArrayList<>();

        cityDTOS.forEach(cityDTO -> cityNames.add(cityDTO.getCityName()));

        model.addAttribute("cityNames", cityNames);
        return "filter-form";
    }

    @GetMapping("/get-data-range")
    @ResponseBody
    public Map<String, String> getMinMaxDate(@RequestParam String cityName) {
        List<String> minMaxDate = airQualityService.getMinMaxDates(cityName);
        Map<String, String> dateRange = new HashMap<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDateTime minDate = LocalDateTime.parse(minMaxDate.get(0), formatter);
        LocalDateTime maxDate = LocalDateTime.parse(minMaxDate.get(minMaxDate.size() - 1), formatter);

        dateRange.put("minDate", minDate.format(outputFormatter));
        dateRange.put("maxDate", maxDate.format(outputFormatter));

        return dateRange;
    }

}
