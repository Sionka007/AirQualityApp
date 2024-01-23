package air.airQuality.AirQualityApp.DTO;


public class CityDTO {
    private String cityName;

    private Double latitude;

    private Double longitude;

    private String country;

    public CityDTO(){}

    public CityDTO(String cityName, Double latitude, Double longitude, String country) {
        this.cityName = cityName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
