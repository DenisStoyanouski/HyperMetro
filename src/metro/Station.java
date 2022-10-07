package metro;

public class Station {
    private String stationName;

    private String[] transfer = null;

    public Station(String stationName) {
        this.stationName = stationName;

    }

    public String getStationName() {
        return stationName;
    }

}
