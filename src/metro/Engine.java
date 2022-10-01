package metro;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Engine {

    static List <Station> metroLine = new ArrayList<>();
    static Station depot = new Station("depot");
    static Station head = depot;
    static Station tail = depot;

    static Station last = depot;

    static void readFile(String fileName) {
        File source = new File(String.format(".\\%s", fileName));
        try (Scanner scanner = new Scanner(source)) {
            while (scanner.hasNextLine()) {
                String stationName = scanner.nextLine();
                if (!stationName.isEmpty()) {
                    createStation(stationName);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error! Such a file doesn't exist!");
        }
        printStations();
    }

    static void createStation(String stationName) {
        Station station = new Station(stationName);
        station.setPrevStation(last);
        last.setNextStation(station);
        station.setNextStation(tail);
        tail.setPrevStation(station);
        last = station;
        metroLine.add(station);
    }

    static void printStations() {
        for (Station station : metroLine) {
            station.printStation();
        }
    }

}
