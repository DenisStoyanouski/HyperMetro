package metro;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Engine {
    final static Map<String, Line> metro = new HashMap<>();

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
        runCommand(getInput());
    }

    static String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    static void runCommand(String input) {
        String[] commands = input.trim().split("\\s");
        String command = commands[0];
        String lineName = commands[1];
        String stationName = commands[2];
        while (true) {
            switch(command) {
                case "/append" : metro.get(lineName).append(new Station(stationName));
                    break;
                case "/add-head" : metro.get(lineName).addHead(new Station(stationName));
                    break;
                case "/remove" : metro.get(lineName).remove(stationName);
                    break;
                case "/output" : metro.get(lineName).output();
                    break;
                case "/exit" : System.exit(0);
                    break;
                default:
                    System.out.println("Invalid command");
                    break;
            }
        }
    }

}
