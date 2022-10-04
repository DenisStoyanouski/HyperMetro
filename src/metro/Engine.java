package metro;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Engine {

    static void readFile(String fileName) {
        File source = new File(String.format(".\\%s", fileName));
        Line line = new Line("firstLine");
        try (Scanner scanner = new Scanner(source)) {
            while (scanner.hasNextLine()) {
                String stationName = scanner.nextLine();
                if (!stationName.isEmpty()) {
                    line.append(new Station(stationName));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error! Such a file doesn't exist!");
        }
        line.output();
    }

}
