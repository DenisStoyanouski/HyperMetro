package metro;
import java.util.*;

public class Line {
    public final String name;
    private final List<Station> line = new LinkedList<>();

    public Line(String name) {
        this.name = name;
        line.add(new Station("depot"));
        line.add(new Station("depot"));
    }

    public Station getStationByName(String stationName) {
        Station currentStation = null;
        for (Station station : line) {
            if (station.getStationName().equals(stationName)) {
                currentStation = station;
            }
        }
        return currentStation;
    }

    public void add(String stationName) {
        line.add(line.size() - 2, new Station(stationName));
    }

    public void addHead(String stationName) {
        line.add(1, new Station(stationName));
    }

    public void addByIndex(int index, String stationName) {
        line.add(index, new Station(stationName));
    }

    public void remove (String stationName) {
        Station currentStation = null;
        for (Station station : line) {
            if (station.getStationName().equals(stationName)) {
                currentStation = station;
            }
        }
        line.remove(currentStation);
    }

    // output all stations in format "previous station - station - next station"
    public void output () {
        for (int i = 1; i < line.size() - 1; i++) {
            System.out.println(line.get(i).getStationName());
        }
    }
}
