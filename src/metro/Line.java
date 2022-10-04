package metro;
import java.util.ArrayList;
import java.util.List;

public class Line {
    private final String name;
    private final List<Station> stations = new ArrayList<>();

    public Line(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void append (Station station) {
        stations.add(station);
        station.setPrevStation(stations.get(stations.indexOf(station) - 1));
        station.setNextStation(stations.get(stations.indexOf(station) + 1));
    }

    //add object Station to first place - after depot
    public void addHead (Station station) {
        stations.add(1, station);
        station.setPrevStation(stations.get(0));
        station.setNextStation(stations.get(2));
    //change links of previous and next stations in line
        stations.get(0).setNextStation(station);
        stations.get(2).setPrevStation(station);
    }

    public void remove (Station station) {
        try {
            // change links of previous and next stations
            stations.get(stations.indexOf(station) - 1).setNextStation(stations.get(stations.indexOf(station) + 1));
            stations.get(stations.indexOf(station) + 1).setPrevStation(stations.get(stations.indexOf(station) - 1));
            //remove station from line
            stations.remove(station);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("Incorrect station");
        }


    }

    // output all stations in format "previous station - station - next station"
    public void output () {
        for (int i = 1; i < stations.size() - 1; i++) {
            Station currentStation = stations.get(i);
            System.out.printf("%s - %s - %s%n", currentStation.getPrevStation().stationName, currentStation.stationName,
                    currentStation.getNextStation().stationName);
        }
    }
}
