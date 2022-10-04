package metro;

import java.util.LinkedList;

public class Line {
    private String name;
    private LinkedList<String> stations = new LinkedList<>();

    public Line(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public LinkedList<String> getStations() {
        return stations;
    }

    public void append (String station) {
        stations.add(station);
    }

    public void addHead (String station) {
        stations.addFirst(station);
    }

    public void remove (String station) {
        stations.remove(station);
    }


}
