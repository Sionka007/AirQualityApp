package air.airQuality.AirQualityApp.Entities;
public enum AirQualityIndex {
    GOOD(1),
    FAIR(2),
    MODERATE(3),
    POOR(4),
    VERY_POOR(5);

    private final int value;

    AirQualityIndex(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static AirQualityIndex getByValue(int value) {
        for (AirQualityIndex index : AirQualityIndex.values()) {
            if (index.value == value) {
                return index;
            }
        }
        throw new IllegalArgumentException("Invalid AirQualityIndex value: " + value);
    }
}
