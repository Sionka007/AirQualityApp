package air.airQuality.AirQualityApp.Controler;

import air.airQuality.AirQualityApp.DTO.AirQualityDataDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/air")
public class HomeController {

    @GetMapping("")
    public String home() {
        return "home";
    }

    @GetMapping("/add-city-new-data")
    public String showAddDataForm(Model model) {
        model.addAttribute("airQualityData", new AirQualityDataDTO());
        return "add-new-data";
    }

    @GetMapping("/success")
    public String showSuccessPage() {
        return "success";
    }

}
