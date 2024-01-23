package air.airQuality.AirQualityApp.DTO;


import air.airQuality.AirQualityApp.Entities.AirQualityIndex;

import java.time.LocalDateTime;

public class AirQualityDataDTO {

    private LocalDateTime timestampDTO;


    private AirQualityIndex airQualityIndexDTO;

    private Double coDTO;
    private Double noDTO;
    private Double no2DTO;
    private Double o3DTO;
    private Double so2DTO;
    private Double pm2_5DTO;
    private Double pm10DTO;
    private Double nh3DTO;
    private CityDTO cityDTO;

    public AirQualityDataDTO() {
    }

    public AirQualityDataDTO(LocalDateTime timestampDTO, AirQualityIndex airQualityIndexDTO, Double coDTO, Double noDTO,
                             Double no2DTO, Double o3DTO, Double so2DTO, Double pm2_5DTO, Double pm10DTO, Double nh3DTO,
                             CityDTO cityDTO) {
        this.timestampDTO = timestampDTO;
        this.airQualityIndexDTO = airQualityIndexDTO;
        this.coDTO = coDTO;
        this.noDTO = noDTO;
        this.no2DTO = no2DTO;
        this.o3DTO = o3DTO;
        this.so2DTO = so2DTO;
        this.pm2_5DTO = pm2_5DTO;
        this.pm10DTO = pm10DTO;
        this.nh3DTO = nh3DTO;
        this.cityDTO = cityDTO;
    }

    public LocalDateTime getTimestampDTO() {
        return timestampDTO;
    }

    public void setTimestampDTO(LocalDateTime timestampDTO) {
        this.timestampDTO = timestampDTO;
    }

    public AirQualityIndex getAirQualityIndexDTO() {
        return airQualityIndexDTO;
    }

    public void setAirQualityIndexDTO(AirQualityIndex airQualityIndexDTO) {
        this.airQualityIndexDTO = airQualityIndexDTO;
    }

    public Double getCoDTO() {
        return coDTO;
    }

    public void setCoDTO(Double coDTO) {
        this.coDTO = coDTO;
    }

    public Double getNoDTO() {
        return noDTO;
    }

    public void setNoDTO(Double noDTO) {
        this.noDTO = noDTO;
    }

    public Double getNo2DTO() {
        return no2DTO;
    }

    public void setNo2DTO(Double no2DTO) {
        this.no2DTO = no2DTO;
    }

    public Double getO3DTO() {
        return o3DTO;
    }

    public void setO3DTO(Double o3DTO) {
        this.o3DTO = o3DTO;
    }

    public Double getSo2DTO() {
        return so2DTO;
    }

    public void setSo2DTO(Double so2DTO) {
        this.so2DTO = so2DTO;
    }

    public Double getPm2_5DTO() {
        return pm2_5DTO;
    }

    public void setPm2_5DTO(Double pm2_5DTO) {
        this.pm2_5DTO = pm2_5DTO;
    }

    public Double getPm10DTO() {
        return pm10DTO;
    }

    public void setPm10DTO(Double pm10DTO) {
        this.pm10DTO = pm10DTO;
    }

    public Double getNh3DTO() {
        return nh3DTO;
    }

    public void setNh3DTO(Double nh3DTO) {
        this.nh3DTO = nh3DTO;
    }

    public CityDTO getCityDTO() {
        return cityDTO;
    }

    public void setCityDTO(CityDTO cityDTO) {
        this.cityDTO = cityDTO;
    }
}
