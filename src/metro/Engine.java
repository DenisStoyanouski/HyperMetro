package metro;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Engine {
    static Map<String, Line> metro = new HashMap<>();

    static void readFile(File file) throws IOException {
        GsonStreamApiRead.read(file);
        runCommand();
    }

    static String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    static void runCommand() {
        String command;

        while (true) {
            String[] commands = getInput().trim().split("(?<!(\"\\w{1,10}))\\s(?!(\\w+\"))");
            command = commands[0];
            try {
                switch(command) {
                    case "/append" : metro.get(commands[1].replaceAll("\"","")).add(commands[2].replaceAll("\"", ""));
                        break;
                    case "/add-head" : metro.get(commands[1].replaceAll("\"","")).addHead(commands[2].replaceAll("\"", ""));
                        break;
                    case "/remove" : metro.get(commands[1].replaceAll("\"","")).remove(commands[2].replaceAll("\"", ""));
                        break;
                    case "/output" : metro.get(commands[1].replaceAll("\"","")).printStation();
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
