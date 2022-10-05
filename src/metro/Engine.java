package metro;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Engine {
    final static Map<String, Line> metro = new HashMap<>();

    static void readFile(String fileName) {
        File source = new File(String.format(".\\%s", fileName));
        Line line = new Line("firstLine");
        metro.put(line.name, line);
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
        runCommand();
    }

    static String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    static void runCommand() {
        String command;

        while (true) {
            String[] commands = getInput().trim().split("(?<!\"\\w{2,20})\\s");
            command = commands[0];
            try {
                switch(command) {
                    case "/append" : metro.get(commands[1]).append(new Station(commands[2].replaceAll("\"", "")));
                        break;
                    case "/add-head" : metro.get(commands[1]).addHead(new Station(commands[2].replaceAll("\"", "")));
                        break;
                    case "/remove" : metro.get(commands[1]).remove(commands[2].replaceAll("\"", ""));
                        break;
                    case "/output" : metro.get(commands[1]).output();
                        break;
                    case "/exit" : System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid command");
                        break;
                }
            } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid command");
            }
        }
    }
}
