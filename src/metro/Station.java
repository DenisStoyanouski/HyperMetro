package metro;

public class Station {
    public String stationName;
    private Station prev;
    private Station next;

    public Station(String stationName) {
        this.prev = null;
        this.stationName = stationName;
        this.next = null;
    }

    public void setPrevStation(Station station) {
        this.prev = station;
    }

    public Station getPrevStation() {
        return this.prev;
    }

    public void setNextStation(Station station) {
        this.next = station;
    }

    public Station getNextStation() {
        return this.next;
    }



}
