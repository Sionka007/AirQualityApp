package air.airQuality.AirQualityApp.utility;

public class AirQualityException extends RuntimeException {

    private final String errorMsg;

    public AirQualityException( String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}

