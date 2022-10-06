package metro;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        String fileName = args[0];
        File file;
        file = new File(fileName);
        Engine.readFile(file);


    }
}
