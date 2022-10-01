package metro;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Engine {

    List <Station> metroLine = new ArrayList<>();
    Station depot = new Station("depot");

    static void readFile(String fileName) {
        File source = new File(String.format(".\\%s", fileName));
        try (Scanner scanner = new Scanner(source)) {
            while (scanner.hasNextLine()) {
                String stationName = scanner.nextLine();
                if(!stationName.isEmpty()) {
                    createStation(scanner.nextLine());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error! Such a file doesn't exist!");
        }
    }

    static void createStation(String stationName) {

    }
}
