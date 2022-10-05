package metro;
import java.util.ArrayList;
import java.util.List;

public class Line {
    public final String name;
    private final List<Station> stations = new ArrayList<>();

    public Line(String name) {
        this.name = name;
        stations.add(new Station("depot"));
        stations.add(new Station("depot"));
    }

    public Station getStation(String stationName) {
        Station currentStation = null;
        for (Station station : stations) {
            if (station.stationName.equals(stationName)) {
                currentStation = station;
            }
        }
        return currentStation;
    }

    public void append (Station station) {
        int index = stations.size() - 1;
        stations.add(index, station);
        station.setPrevStation(stations.get(index - 1));
        station.setNextStation(stations.get(index + 1));

        stations.get(index - 1).setNextStation(station);
        stations.get(index + 1).setPrevStation(station);

    }
    public void addByIndex(int index, Station station) {
        stations.add(index, station);
        station.setPrevStation(stations.get(index - 1));
        station.setNextStation(stations.get(index + 1));

        stations.get(index - 1).setNextStation(station);
        stations.get(index + 1).setPrevStation(station);
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

    public void remove (String stationName) {
        Station currentStation = null;
        for (Station station : stations) {
            if (station.stationName.equals(stationName)) {
                currentStation = station;
            }
        }
        try {
            // change links of previous and next stations
            int index = stations.indexOf(currentStation);
            stations.get(index - 1).setNextStation(stations.get(index + 1));
            stations.get(index + 1).setPrevStation(stations.get(index - 1));
            //remove station from line
            stations.remove(currentStation);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("Incorrect station name");
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
